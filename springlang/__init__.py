import os

from textx import generator as gen

from springlang.main import generate


@gen(language="SpringLang", target="spring")
def entity_gen(metamodel, model, output_path, overwrite, debug):
    filename = model._tx_filename

    if not output_path:
        output_path = os.path.dirname(filename)

    generate(model_path=filename, output_path=output_path, metamodel=metamodel)

