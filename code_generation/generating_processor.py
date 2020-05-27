from os.path import join

from const.constants import *
from utilities.directory_utils import create_directory
from utilities.generate_switcher import generate
from utilities.template_utils import extract_package_segments, entity_package_path, get_application_name


def generate_app(render_args, environment, entities, metamodel):
    app_name, package_paths, model_configs, resource_package_path = get_metadata(metamodel, 'model/model.ent')

    templates = get_templates(jinja_environment=environment)
    generate_commons(render_args, templates, package_paths)

    jsp_directory = create_directory(join(package_paths.get('webapp'), WEB_INF, JSP))

    for entity in entities:
        package_path_qname = entity_package_path(entity=entity)
        package_path_segments = extract_package_segments(package_path_qname)
        java_package_path = join(package_paths.get('root'), JAVA, *package_path_segments)

        render_args = {
            "entity": entity,
            "packagePath": package_path_qname,
            "app_name": app_name,
            "configs": model_configs
        }
        entity_name = entity.name

        model_directory = create_directory(join(java_package_path, "bom"))
        service_directory = create_directory(join(java_package_path, "service"))
        controller_directory = create_directory(join(java_package_path, "controller"))
        dto_directory = create_directory(join(java_package_path, "dto"))
        converter_directory = create_directory(join(java_package_path, "converter"))
        repo_directory = create_directory(join(java_package_path, "repository"))

        generate(model_directory, templates[BOM_TEMPLATE], BOM_TEMPLATE, render_args, entity_name)
        generate(repo_directory, templates[ENTITY_REPOSITORY_TEMPLATE], ENTITY_REPOSITORY_TEMPLATE, render_args,
                 entity_name)
        generate(service_directory, templates[SERVICE_TEMPLATE], SERVICE_TEMPLATE, render_args, entity_name)
        generate(service_directory, templates[SERVICE_IMPLEMENTATION_TEMPLATE], SERVICE_IMPLEMENTATION_TEMPLATE,
                 render_args, entity_name)
        generate(controller_directory, templates[CONTROLLER_TEMPLATE], CONTROLLER_TEMPLATE, render_args, entity_name)
        generate(dto_directory, templates[DTO_TEMPLATE], DTO_TEMPLATE, render_args, entity_name)
        generate(converter_directory, templates[CONVERTER_ENTITY_TO_DTO_TEMPLATE], CONVERTER_ENTITY_TO_DTO_TEMPLATE,
                 render_args, entity_name)
        generate(converter_directory, templates[CONVERTER_DTO_TO_ENTITY_TEMPLATE], CONVERTER_DTO_TO_ENTITY_TEMPLATE,
                 render_args, entity_name)
        generate(jsp_directory, templates[ENTITY_BASE_PAGE_TEMPLATE], ENTITY_BASE_PAGE_TEMPLATE, render_args,
                 entity_name)
        generate(jsp_directory, templates[ENTITY_OVERVIEW_TEMPLATE], ENTITY_OVERVIEW_TEMPLATE, render_args, entity_name)



def get_templates(jinja_environment):
    """
    Register your new templates here

    :param jinja_environment: configured jinja environment provided from main
    :return: templates dictionary
    """

    templates = {
        BOM_TEMPLATE: jinja_environment.get_template(BOM_TEMPLATE_FILE),
        BASE_REPOSITORY_TEMPLATE: jinja_environment.get_template(BASE_REPOSITORY_TEMPLATE_FILE),
        BASE_REPOSITORY_IMPL_TEMPLATE: jinja_environment.get_template(BASE_REPOSITORY_IMPL_TEMPLATE_FILE),
        ENTITY_REPOSITORY_TEMPLATE: jinja_environment.get_template(ENTITY_REPOSITORY_TEMPLATE_FILE),
        SERVICE_TEMPLATE: jinja_environment.get_template(SERVICE_TEMPLATE_FILE),
        SERVICE_IMPLEMENTATION_TEMPLATE: jinja_environment.get_template(SERVICE_IMPLEMENTATION_FILE),
        MAIN_TEMPLATE: jinja_environment.get_template(MAIN_TEMPLATE_FILE),
        CONTROLLER_TEMPLATE: jinja_environment.get_template(CONTROLLER_TEMPLATE_FILE),
        DTO_TEMPLATE: jinja_environment.get_template(DTO_TEMPLATE_FILE),
        CONVERTER_ENTITY_TO_DTO_TEMPLATE: jinja_environment.get_template(CONVERTER_ENTITY_TO_DTO_TEMPLATE_FILE),
        CONVERTER_DTO_TO_ENTITY_TEMPLATE: jinja_environment.get_template(CONVERTER_DTO_TO_ENTITY_TEMPLATE_FILE),
        NAVBAR_TEMPLATE: jinja_environment.get_template(NAVBAR_TEMPLATE_FILE),
        ENTITY_BASE_PAGE_TEMPLATE: jinja_environment.get_template(ENTITY_BASE_PAGE_TEMPLATE_FILE),
        ENTITY_OVERVIEW_TEMPLATE: jinja_environment.get_template(ENTITY_OVERVIEW_TEMPLATE_FILE),
        APPLICATION_YML_TEMPLATE: jinja_environment.get_template(APPLICATION_YML_TEMPLATE_FILE)
    }

    return templates


def get_metadata(metamodel, model_path):
    """
    Helper function that returns data needed for code generation

    :param metamodel:
    :param model_path: path where .ent file is placed
    :return: application name, packages (root, java, resources, webapp) and configs from .ent model
    """

    user_model = metamodel.model_from_file(model_path)
    app_name = get_application_name(user_model.configs)

    package_path_segments = extract_package_segments(GENERATED_ROOT_PACKAGE)
    root = join(GENERATED_APP_DIRECTORY, SRC, MAIN)
    java_package_path = join(root, JAVA, *package_path_segments)
    resources_package_path = join(root, RESOURCES)
    webapp_package_path = join(root, WEBAPP)

    package_paths = {
        'root': root,
        'java': java_package_path,
        'resources': resources_package_path,
        'webapp': webapp_package_path
    }

    model_configs = user_model.configs

    return app_name, package_paths, model_configs, resources_package_path


def generate_commons(render_args, templates, package_paths):
    """
    Helper function which generates code which is not dependent on specific entity.

    :param render_args: contains basic information about application
    :param templates: dictionary that contain all templates key-value pairs
    :param package_paths: dictionary which holds root, java, resources and webapp paths
    """

    main_directory = create_directory(package_paths.get('java'))
    repository_directory = create_directory(join(package_paths.get('java'), 'repository'))
    jsp_directory = create_directory(join(package_paths.get('webapp'), WEB_INF, JSP))
    resource_directory = create_directory(join(package_paths.get('resources')))

    generate(main_directory, templates[MAIN_TEMPLATE], MAIN_TEMPLATE, render_args)
    generate(repository_directory, templates[BASE_REPOSITORY_TEMPLATE], BASE_REPOSITORY_TEMPLATE, render_args)
    generate(repository_directory, templates[BASE_REPOSITORY_IMPL_TEMPLATE], BASE_REPOSITORY_IMPL_TEMPLATE, render_args)
    generate(jsp_directory, templates[NAVBAR_TEMPLATE], NAVBAR_TEMPLATE, render_args)
    generate(resource_directory, templates[APPLICATION_YML_TEMPLATE], APPLICATION_YML_TEMPLATE, render_args)
