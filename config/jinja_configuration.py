from main import this_folder
from utilities.jinja_utils import setup_jinja_environment, set_filters, set_globals


def configure_environment():
    environment = setup_jinja_environment(this_folder)
    set_filters(environment)
    set_globals(environment)
    return environment
