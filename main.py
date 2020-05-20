from os.path import join, dirname

from textx import metamodel_from_file
from textx.export import metamodel_export, model_export

from config import jinja_configuration
from const.dot_export import META_MODEL_DOT, MODEL_DOT, DIRECTORY_NAME
from utilities.jinja_utils import *
from utilities.template_utils import get_application_name, get_jsp_metadata

this_folder = dirname(__file__)


def get_entity_metamodel():
    simple_types = {
        BYTE: SimpleType(None, BYTE),
        SHORT: SimpleType(None, SHORT),
        INT: SimpleType(None, INT),
        LONG: SimpleType(None, LONG),
        FLOAT: SimpleType(None, FLOAT),
        DOUBLE: SimpleType(None, DOUBLE),
        BOOLEAN: SimpleType(None, BOOLEAN),
        CHAR: SimpleType(None, CHAR),
        STRING: SimpleType(None, STRING)
    }
    return metamodel_from_file('meta/entity.tx', classes=[SimpleType], builtins=simple_types)


def export_models(metamodel, model, dot_directory):
    metamodel_export(metamodel, join(dot_directory, META_MODEL_DOT))
    model_export(model, join(dot_directory, MODEL_DOT))


def main():
    from utilities.directory_utils import create_directory

    environment = jinja_configuration.configure_environment()
    metamodel = get_entity_metamodel()

    user_model = metamodel.model_from_file('model/model.ent')
    app_name = get_application_name(user_model.configs)

    bootstrap_css, bootstrap_js = get_jsp_metadata()
    render_args = {"app_name": app_name, "configs": user_model.configs, "bootstrap_css": bootstrap_css,
                   "bootstrap_js": bootstrap_js, "entities": user_model.entities}

    from code_generation.generating_processor import generate_app
    generate_app(render_args, environment, entities=user_model.entities, metamodel=metamodel)

    dot_directory = create_directory(DIRECTORY_NAME)
    export_models(metamodel, user_model, dot_directory=dot_directory)


if __name__ == "__main__":
    main()
