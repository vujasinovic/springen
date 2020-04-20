BOM_TEMPLATE = 'bom_template'
BASE_REPOSITORY_TEMPLATE = 'base_repository_template'
BASE_REPOSITORY_IMPL_TEMPLATE = 'base_repository_impl_template'
ENTITY_REPOSITORY_TEMPLATE = 'entity_repository_template'

BOM_TEMPLATE_FILE = 'template/bom.template'
BASE_REPOSITORY_TEMPLATE_FILE = 'template/base_repository.template'
BASE_REPOSITORY_IMPL_TEMPLATE_FILE = 'template/base_repository_impl.template'
ENTITY_REPOSITORY_TEMPLATE_FILE = 'template/entity_repository.template'


def entity_package_path(entity):
    if entity.belongsTo is None:
        default_path = 'rs.ftn'
        return default_path
    package_names_reversed = entity_package_uri(entity.parent, entity.belongsTo.package)
    package_names = package_names_reversed[::-1]
    full_path = '.'.join(package_names)
    return full_path


def entity_package_uri(entity_model, current_package, package_names=[]):
    package_names.append(current_package.name)
    if current_package.parent is None or current_package.parent is entity_model:
        return package_names
    else:
        return entity_package_uri(entity_model, current_package.parent, package_names)


def get_application_name(configs_field):
    default_app_name = 'demo'
    if configs_field is None:
        return default_app_name

    app_name_param = next((param for param in configs_field.config_params if param.key == 'applicationName'), None)
    if app_name_param:
        return app_name_param.value
    else:
        return default_app_name


def write_to_file(content, file_name):
    with open(file_name, 'w') as file:
        file.write(content)


def get_templates(jinja_environment):
    bom_template = jinja_environment.get_template(BOM_TEMPLATE_FILE)
    base_repository_template = jinja_environment.get_template(BASE_REPOSITORY_TEMPLATE_FILE)
    base_repository_impl_template = jinja_environment.get_template(BASE_REPOSITORY_IMPL_TEMPLATE_FILE)
    repository_template = jinja_environment.get_template(ENTITY_REPOSITORY_TEMPLATE_FILE)

    templates_dict = {
            BOM_TEMPLATE: bom_template,
            BASE_REPOSITORY_TEMPLATE: base_repository_template,
            BASE_REPOSITORY_IMPL_TEMPLATE: base_repository_impl_template,
            ENTITY_REPOSITORY_TEMPLATE: repository_template
    }

    return templates_dict
