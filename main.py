from os.path import join, dirname

from textx import metamodel_from_file
from textx.export import metamodel_export, model_export

from config import jinja_configuration
from const.dot_export import META_MODEL_DOT, MODEL_DOT, DIRECTORY_NAME
from utilities.classes import SimpleType
from utilities.jinja_utils import *
from utilities.template_utils import entity_package_path

GENERATED_APP_DIRECTORY = 'generated_app'

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

    bom_template = environment.get_template("template/bom.template")
    model_directory = create_directory(join(GENERATED_APP_DIRECTORY, "bom"))

    repository_template = environment.get_template("template/repository.template")
    generic_repository_template = environment.get_template("template/generic_repository.template")
    repository_directory = create_directory(join(GENERATED_APP_DIRECTORY, "repository"))

    user_model = metamodel.model_from_file('model/model.ent')

    for entity in user_model.entities:
        with open(join(model_directory, '%s.java' % entity.name), 'w') as file:
            file.write(bom_template.render(entity=entity))
        with open(join(repository_directory, '%sRepository.java' % entity.name), 'w') as file:
            file.write(repository_template.render(entity=entity, packagePath=entity_package_path(entity=entity)))

    with open(join(repository_directory, 'GenericRepository.java'), 'w') as file:
        file.write(generic_repository_template.render(entity=entity, configs=user_model.configs))

    directory = create_directory(DIRECTORY_NAME)
    export_models(metamodel, user_model, dot_directory=directory)


if __name__ == "__main__":
    main()
