from utilities.classes import SimpleType


def to_pascalcase(st):
    return st[0].upper() + st[1:]


def to_lowercase(st):
    return st[0].lower() + st[1:]


def get_last_character(st):
    return st[-1];


def plural(st):
    return st + 'es' if get_last_character(st) == 's' else st + 's'


def is_simple_type(obj_type):
    return isinstance(obj_type, SimpleType)

def template_name(template):
    full_qualified_name = template._TemplateReference__context.name
    begin_ind = full_qualified_name.rfind('/') + 1
    if begin_ind > 0:
        file_name = full_qualified_name[begin_ind:]
        return file_name
    else:
        return full_qualified_name


def get_config_param(config_params, attribute_value):
    param_value = next((param for param in config_params if param.key == attribute_value), None)
    return param_value

