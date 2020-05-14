from os.path import join, dirname, sep

from textx import metamodel_from_file
from textx.export import metamodel_export, model_export

from config import jinja_configuration
from const.constants import *
from const.dot_export import META_MODEL_DOT, MODEL_DOT, DIRECTORY_NAME
from utilities.jinja_utils import *
from utilities.generate_switcher import generate
from utilities.template_utils import entity_package_path, get_application_name, write_to_file, get_templates, \
    get_jsp_metadata, extract_package_segments

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

    package_path_segments = extract_package_segments(GENERATED_ROOT_PACKAGE)
    package_path = join(GENERATED_APP_DIRECTORY, *package_path_segments)

    main_directory = create_directory(package_path)
    repository_directory = create_directory(join(package_path, "repository"))
    jsp_directory = create_directory(join(package_path, "jsp"))

    templates_dict = get_templates(environment)

    user_model = metamodel.model_from_file('model/model.ent')
    app_name = get_application_name(user_model.configs)

    bootstrap_css, bootstrap_js = get_jsp_metadata()
    general_render_args = {"app_name": app_name, "configs": user_model.configs, "bootstrap_css": bootstrap_css, "bootstrap_js": bootstrap_js, "entities": user_model.entities}

    generate(main_directory, templates_dict[MAIN_TEMPLATE], MAIN_TEMPLATE, general_render_args)
    generate(repository_directory, templates_dict[BASE_REPOSITORY_TEMPLATE], BASE_REPOSITORY_TEMPLATE, general_render_args)
    generate(repository_directory, templates_dict[BASE_REPOSITORY_IMPL_TEMPLATE], BASE_REPOSITORY_IMPL_TEMPLATE, general_render_args)
    generate(jsp_directory, templates_dict[NAVBAR_TEMPLATE], NAVBAR_TEMPLATE, general_render_args)

    for entity in user_model.entities:
        package_path_qname = entity_package_path(entity=entity)
        package_path_segments = extract_package_segments(package_path_qname)
        package_path = join(GENERATED_APP_DIRECTORY, *package_path_segments)

        render_args = {
            "entity": entity,
            "packagePath": package_path_qname,
            "app_name": app_name,
            "configs": user_model.configs
        }
        entity_name = entity.name

        model_directory = create_directory(join(package_path, "bom"))
        service_directory = create_directory(join(package_path, "service"))
        controller_directory = create_directory(join(package_path, "controller"))
        dto_directory = create_directory(join(package_path, "dto"))
        converter_directory = create_directory(join(package_path, "converter"))
        repo_directory = create_directory(join(package_path, "repository"))

        generate(model_directory, templates_dict[BOM_TEMPLATE], BOM_TEMPLATE, render_args, entity_name)
        generate(repo_directory, templates_dict[ENTITY_REPOSITORY_TEMPLATE], ENTITY_REPOSITORY_TEMPLATE, render_args, entity_name)
        generate(service_directory, templates_dict[SERVICE_TEMPLATE], SERVICE_TEMPLATE, render_args, entity_name)
        generate(service_directory, templates_dict[SERVICE_IMPLEMENTATION_TEMPLATE], SERVICE_IMPLEMENTATION_TEMPLATE, render_args, entity_name)
        generate(controller_directory, templates_dict[CONTROLLER_TEMPLATE], CONTROLLER_TEMPLATE, render_args, entity_name)
        generate(dto_directory, templates_dict[DTO_TEMPLATE], DTO_TEMPLATE, render_args, entity_name)
        generate(converter_directory, templates_dict[CONVERTER_ENTITY_TO_DTO_TEMPLATE], CONVERTER_ENTITY_TO_DTO_TEMPLATE, render_args, entity_name)
        generate(converter_directory, templates_dict[CONVERTER_DTO_TO_ENTITY_TEMPLATE], CONVERTER_DTO_TO_ENTITY_TEMPLATE, render_args, entity_name)
        generate(jsp_directory, templates_dict[ENTITY_BASE_PAGE_TEMPLATE], ENTITY_BASE_PAGE_TEMPLATE, render_args, entity_name)

    directory = create_directory(DIRECTORY_NAME)
    export_models(metamodel, user_model, dot_directory=directory)


if __name__ == "__main__":
    main()
