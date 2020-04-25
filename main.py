from os.path import join, dirname

from textx import metamodel_from_file
from textx.export import metamodel_export, model_export

from config import jinja_configuration
from const.dot_export import META_MODEL_DOT, MODEL_DOT, DIRECTORY_NAME
from utilities.classes import SimpleType
from utilities.jinja_utils import *
from utilities.template_utils import entity_package_path, get_application_name, write_to_file, get_templates

GENERATED_APP_DIRECTORY = 'generated_app'

BOM_TEMPLATE = 'bom_template'
BASE_REPOSITORY_TEMPLATE = 'base_repository_template'
BASE_REPOSITORY_IMPL_TEMPLATE = 'base_repository_impl_template'
ENTITY_REPOSITORY_TEMPLATE = 'entity_repository_template'
SERVICE_TEMPLATE = 'service_template'
SERVICE_IMPLEMENTATION_TEMPLATE = 'service_implementation_template'
MAIN_TEMPLATE = 'main_template'

this_folder = dirname(__file__)


def get_entity_metamodel():
    simple_types = {
        BYTE: SimpleType(None, BYTE),
        SHORT: SimpleType(None, SHORT),
        INT: SimpleType(None, INT),
        LONG: SimpleType(None, LONG),
        FLOAT: SimpleType(None, FLOAT),
        DOUBLE: SimpleType(None, DOUBLE),
        BOOLEAN: SimpleType(None, BOOLEAN),
        CHAR: SimpleType(None, CHAR),
        STRING: SimpleType(None, STRING)
    }
    return metamodel_from_file('meta/entity.tx', classes=[SimpleType], builtins=simple_types)


def export_models(metamodel, model, dot_directory):
    metamodel_export(metamodel, join(dot_directory, META_MODEL_DOT))
    model_export(model, join(dot_directory, MODEL_DOT))


def main():
    from utilities.directory_utils import create_directory

    environment = jinja_configuration.configure_environment()
    metamodel = get_entity_metamodel()

    model_directory = create_directory(join(GENERATED_APP_DIRECTORY, "bom"))
    repository_directory = create_directory(join(GENERATED_APP_DIRECTORY, "repository"))
    service_directory = create_directory(join(GENERATED_APP_DIRECTORY, "service"))
    main_directory = create_directory(join(GENERATED_APP_DIRECTORY, "main"))


    templates_dict = get_templates(environment)

    user_model = metamodel.model_from_file('model/model.ent')
    app_name = get_application_name(user_model.configs)

    write_to_file(templates_dict[BASE_REPOSITORY_TEMPLATE].render(configs=user_model.configs, app_name=app_name),
                  join(repository_directory, to_pascalcase(app_name) + 'Repository.java'))
    write_to_file(templates_dict[BASE_REPOSITORY_IMPL_TEMPLATE].render(configs=user_model.configs, app_name=app_name),
                  join(repository_directory, to_pascalcase(app_name) + 'RepositoryImpl.java'))

    for entity in user_model.entities:
        packagePath = entity_package_path(entity=entity)
        write_to_file(templates_dict[BOM_TEMPLATE].render(entity=entity),
                      join(model_directory, '%s.java' % entity.name))
        write_to_file(templates_dict[ENTITY_REPOSITORY_TEMPLATE].render(
            entity=entity, packagePath=packagePath, configs=user_model.configs,
            app_name=app_name),
            join(repository_directory, '%sRepository.java' % entity.name))
        write_to_file(templates_dict[SERVICE_TEMPLATE].render(
            entity=entity, packagePath=packagePath, configs=user_model.configs),
            join(service_directory, '%sService.java' % entity.name))
        write_to_file(templates_dict[SERVICE_IMPLEMENTATION_TEMPLATE].render(
            entity=entity, packagePath=packagePath, configs=user_model.configs),
            join(service_directory, '%sServiceImpl.java' % entity.name))
        write_to_file(templates_dict[MAIN_TEMPLATE].render(
            configs=user_model.configs,
            app_name=app_name),
            join(main_directory, to_pascalcase(app_name) + 'Application.java'))

    directory = create_directory(DIRECTORY_NAME)
    export_models(metamodel, user_model, dot_directory=directory)


if __name__ == "__main__":
    main()
