from springlang.const.constants import *


def entity_package_path(entity):
    if entity.belongsTo is None:
        default_path = GENERATED_ROOT_PACKAGE
        return default_path
    package_names_reversed = entity_package_uri(entity.parent, entity.belongsTo.package, [])
    package_names = package_names_reversed[::-1]
    full_path = full_package_path(package_names)
    return full_path


def full_package_path(package_segments):
    root_package_segments = extract_package_segments(GENERATED_ROOT_PACKAGE)
    full_package_segments = root_package_segments + package_segments
    full_path = '.'.join(full_package_segments)
    return full_path


def extract_package_segments(package_path):
    return package_path.split(".")


def entity_package_uri(entity_model, current_package, package_names=[]):
    package_names.append(current_package.name)
    if current_package.parent is None or current_package.parent is entity_model:
        return package_names
    else:
        return entity_package_uri(entity_model, current_package.parent, package_names)


def get_application_name(configs_field):
    default_app_name = DEFAULT_APP_NAME
    if configs_field is None:
        return default_app_name

    app_name_param = next((param for param in configs_field.config_params if param.key == 'applicationName'), None)
    if app_name_param:
        return app_name_param.value
    else:
        return default_app_name


def get_jsp_metadata():
    bootstrap_css = 'https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css'
    bootstrap_js = 'https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js'
    jquery = 'https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js'
    datatables_css = 'https://cdn.datatables.net/1.10.21/css/jquery.dataTables.css'
    datatables_js = 'https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js'
    return bootstrap_css, bootstrap_js, jquery, datatables_css, datatables_js


def write_to_file(content, file_name):
    with open(file_name, 'w') as file:
        file.write(content)
