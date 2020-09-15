from springlang.utilities.jinja_utils import setup_jinja_environment, set_filters, set_globals


def configure_environment(folder):
    environment = setup_jinja_environment(folder)
    set_filters(environment)
    set_globals(environment)
    return environment
