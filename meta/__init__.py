from os.path import dirname, join

from textx import language, metamodel_from_file

from utilities.classes import SimpleType

simple_types = {
    'byte': SimpleType('byte'),
    'short': SimpleType('short'),
    'int': SimpleType('int'),
    'long': SimpleType('long'),
    'float': SimpleType('float'),
    'double': SimpleType('double'),
    'boolean': SimpleType('boolean'),
    'char': SimpleType('char'),
    'string': SimpleType('string')
}


@language("entity", "*.ent")
def entity():
    "A language for describing entity..."
    return get_entity_metamodel()


def get_entity_metamodel():
    return metamodel_from_file(join(dirname(__file__), "entity.tx"), classes=[SimpleType], builtins=simple_types)
