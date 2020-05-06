from os.path import dirname, join

from textx import language, metamodel_from_file


@language("entity", "*.ent")
def entity():
    "A language for describing entity..."
    return get_entity_metamodel()


def get_entity_metamodel():
    simple_types = {
        'byte': SimpleType(None, 'byte'),
        'short': SimpleType(None, 'short'),
        'int': SimpleType(None, 'int'),
        'long': SimpleType(None, 'long'),
        'float': SimpleType(None, 'float'),
        'double': SimpleType(None, 'double'),
        'boolean': SimpleType(None, 'boolean'),
        'char': SimpleType(None, 'char'),
        'string': SimpleType(None, 'string')
    }
    return metamodel_from_file(join(dirname(__file__), "entity.tx"), classes=[SimpleType], builtins=simple_types)


class SimpleType(object):
    def __init__(self, parent, name):
        self.parent = parent
        self.name = name

    def __str__(self):
        return self.name