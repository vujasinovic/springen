from os.path import join, dirname

from textx import metamodel_from_file, GeneratorDesc
from textx.export import metamodel_export, model_export

from springlang.config import jinja_configuration
from springlang.const.dot_export import *
from springlang.const.simpletypes import *
from springlang.utilities.classes import *
from springlang.utilities.entity_parser import parse_entities
from springlang.utilities.template_utils import get_application_name, get_jsp_metadata

this_folder = dirname(__file__)

def get_entity_metamodel(metamodel_path):
    simple_types = {
        BYTE: SimpleType(BYTE),
        SHORT: SimpleType(SHORT),
        INT: SimpleType(INT),
        LONG: SimpleType(LONG),
        FLOAT: SimpleType(FLOAT),
        DOUBLE: SimpleType(DOUBLE),
        BOOLEAN: SimpleType(BOOLEAN),
        CHAR: SimpleType(CHAR),
        STRING: SimpleType(STRING)
    }
    return metamodel_from_file(metamodel_path, classes=[SimpleType], builtins=simple_types)


def export_models(metamodel, model, dot_directory):
    metamodel_export(metamodel, join(dot_directory, META_MODEL_DOT))
    model_export(model, join(dot_directory, MODEL_DOT))


def generate(model_path, metamodel, output_path):
    from springlang.utilities.directory_utils import create_directory

    environment = jinja_configuration.configure_environment(this_folder)

    user_model = metamodel.model_from_file(model_path)
    app_name = get_application_name(user_model.configs)

    bootstrap_css, bootstrap_js, jquery, datatables_css, datatables_js = get_jsp_metadata()
    render_args = {"app_name": app_name, "configs": user_model.configs, "entities": user_model.entities,
                   "bootstrap_css": bootstrap_css, "bootstrap_js": bootstrap_js, "jquery": jquery,
                   "datatables_css": datatables_css, "datatables_js": datatables_js}

    from springlang.code_generation.generating_processor import generate_app
    parsed_entities = parse_entities(user_model.entities)
    generate_app(render_args, environment, entities=parsed_entities, metamodel=metamodel, model_path=model_path, output_path=output_path, folder="")

    dot_directory = create_directory(DIRECTORY_NAME, this_folder)
    export_models(metamodel, user_model, dot_directory=dot_directory)


if __name__ == "__main__":
    generate('springlang/model/model.ent', get_entity_metamodel('springlang/meta/entity.tx'), 'generated_app')
