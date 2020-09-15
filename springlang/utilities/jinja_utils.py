import datetime
from os import environ
import jinja2

from springlang.const import javatypes as javatypes
from springlang.const.constants import GENERATED_ROOT_PACKAGE
from springlang.const.simpletypes import *
from springlang.filters.filters import *
from springlang.utilities.classes import Property


def dto_declaration(property):
    return declaration(property, 'Dto')


def declaration(property, suffix=''):
    return javatype(property, suffix) + ' ' + property.name


def javatype(s, suffix=''):
    if isinstance(s, SimpleType):
        return {
                   STRING: javatypes.STRING,
                   CHAR: javatypes.CHAR,
                   BOOLEAN: javatypes.BOOLEAN,
                   DOUBLE: javatypes.DOUBLE,
                   FLOAT: javatypes.FLOAT,
                   LONG: javatypes.LONG,
                   INT: javatypes.INT,
                   SHORT: javatypes.SHORT,
                   BYTE: javatypes.BYTE,
               }.get(s.name, s.name) + suffix
    elif isinstance(s, Property):
        typeName = javatype(s.type) + suffix
        if s.list:
            typeName = 'List<' + typeName + '>'

        return typeName


def getter(name):
    return "get" + to_pascalcase(name)


def setter(name):
    return "get" + to_pascalcase(name)


def to_dto(s):
    if isinstance(s, SimpleType):
        dto = javatype(s, 'Dto')
    elif isinstance(s, Property):
        dto = javatype(s, 'Dto')
    else:
        dto = s + 'Dto'

    return dto


def to_dto_type(property):
    suffix = ''
    if property.type.is_user_type:
        suffix = 'Dto'

    return javatype(property, suffix)


def setup_jinja_environment(source):
    return jinja2.Environment(
        loader=jinja2.FileSystemLoader(source),
        trim_blocks=True,
        lstrip_blocks=True,
        extensions=set_extensions()
    )


def set_filters(jinja_environment):
    jinja_environment.filters['declaration'] = declaration
    jinja_environment.filters['dto_declaration'] = dto_declaration
    jinja_environment.filters['javatype'] = javatype
    jinja_environment.filters['getter'] = getter
    jinja_environment.filters['setter'] = setter
    jinja_environment.filters['to_dto_type'] = to_dto_type
    jinja_environment.filters['to_pascalcase'] = to_pascalcase
    jinja_environment.filters['to_lowercase'] = to_lowercase
    jinja_environment.filters['template_name'] = template_name
    jinja_environment.filters['config_param'] = get_config_param
    jinja_environment.filters['last_character'] = get_last_character
    jinja_environment.filters['to_plural'] = plural
    jinja_environment.filters['is_simple_type'] = is_simple_type
    jinja_environment.filters['to_dto'] = to_dto


def set_globals(jinja_environment):
    jinja_environment.globals['now'] = datetime.datetime.now()
    jinja_environment.globals['author'] = environ.get('username', 'Author')
    jinja_environment.globals['version'] = 1
    jinja_environment.globals['default_package'] = GENERATED_ROOT_PACKAGE


def set_extensions():
    return ['jinja2.ext.i18n', 'jinja2.ext.debug']
