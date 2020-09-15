from textx import language

from springlang.main import get_entity_metamodel


@language("SpringLang", "*.ent")
def entity():
    "A language for describing entity..."
    return get_entity_metamodel('/home/pavle/Desktop/model/entity.tx')