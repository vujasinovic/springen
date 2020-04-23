import datetime
from os import environ
import jinja2

from const import javatypes as javatypes
from const.simpletypes import *
from filters.filters import *


def javatype(s):
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
    }.get(s.name, s.name)


def setup_jinja_environment(source):
    return jinja2.Environment(
        loader=jinja2.FileSystemLoader(source),
        trim_blocks=True,
        lstrip_blocks=True,
        extensions=set_extensions()
    )


def set_filters(jinja_environment):
    jinja_environment.filters['javatype'] = javatype
    jinja_environment.filters['to_pascalcase'] = to_pascalcase
    jinja_environment.filters['to_lowercase'] = to_lowercase
    jinja_environment.filters['template_name'] = template_name
    jinja_environment.filters['config_param'] = get_config_param


def set_globals(jinja_environment):
    jinja_environment.globals['now'] = datetime.datetime.now()
    jinja_environment.globals['author'] = environ.get('username', 'Author')
    jinja_environment.globals['version'] = 1
    jinja_environment.globals['default_package'] = 'rs.ftn'


def set_extensions():
    return ['jinja2.ext.i18n', 'jinja2.ext.debug']


