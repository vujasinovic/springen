from os.path import join

from const.constants import *
from filters.filters import to_pascalcase, plural, to_lowercase
from utilities.template_utils import write_to_file


def main(directory, app_name, entity_name):
    return join(directory, to_pascalcase(app_name) + 'Application.java')

def pom(directory, app_name, entity_name):
    return join(directory, 'pom.xml')

def proto(directory, app_name, entity_name):
    return join(directory, 'main.proto')

def base_repository(directory, app_name, entity_name):
    return join(directory, to_pascalcase(app_name) + 'Repository.java')


def base_repository_impl(directory, app_name, entity_name):
    return join(directory, to_pascalcase(app_name) + 'RepositoryImpl.java')


def navbar(directory, app_name, entity_name):
    return join(directory, 'navbar.jsp')


def bom(directory, app_name, entity_name):
    return join(directory, '%s.java' % entity_name)


def entity_repository(directory, app_name, entity_name):
    return join(directory, '%sRepository.java' % entity_name)


def service(directory, app_name, entity_name):
    return join(directory, '%sService.java' % entity_name)

def grpc_service(directory, app_name, entity_name):
    return join(directory, '%sGrpcService.java' % entity_name)


def service_implementation(directory, app_name, entity_name):
    return join(directory, '%sServiceImpl.java' % entity_name)


def controller(directory, app_name, entity_name):
    return join(directory, '%sController.java' % entity_name)


def dto(directory, app_name, entity_name):
    return join(directory, '%sDto.java' % entity_name)


def converter_entity_to_dto(directory, app_name, entity_name):
    return join(directory, '%sTo%sDtoConverter.java' % (entity_name, entity_name))


def converter_dto_to_entity(directory, app_name, entity_name):
    return join(directory, '%sDtoTo%sConverter.java' % (entity_name, entity_name))


def entity_base_page(directory, app_name, entity_name):
    return join(directory, '%s.jsp' % plural(to_lowercase(entity_name)))


def form_template(directory, app_name, entity_name):
    return join(directory, '%s.jsp' % to_lowercase('create' + entity_name))


def entity_overview_page(directory, app_name, entity_name):
    return join(directory, '%s.jsp' % to_lowercase(entity_name))


def application_yml(directory, app_name, entity_name):
    return join(directory, 'application.yml')


def generate(directory, template, template_name, render_args, entity_name=""):
    switcher = {
        MAIN_TEMPLATE: main,
        POM_TEMPLATE: pom,
        PROTO_TEMPLATE: proto,
        BASE_REPOSITORY_TEMPLATE: base_repository,
        BASE_REPOSITORY_IMPL_TEMPLATE: base_repository_impl,
        NAVBAR_TEMPLATE: navbar,
        BOM_TEMPLATE: bom,
        ENTITY_REPOSITORY_TEMPLATE: entity_repository,
        GRPC_SERVICE_TEMPLATE: grpc_service,
        SERVICE_TEMPLATE: service,
        SERVICE_IMPLEMENTATION_TEMPLATE: service_implementation,
        CONTROLLER_TEMPLATE: controller,
        DTO_TEMPLATE: dto,
        CONVERTER_ENTITY_TO_DTO_TEMPLATE: converter_entity_to_dto,
        CONVERTER_DTO_TO_ENTITY_TEMPLATE: converter_dto_to_entity,
        ENTITY_BASE_PAGE_TEMPLATE: entity_base_page,
        ENTITY_OVERVIEW_TEMPLATE: entity_overview_page,
        FORM_TEMPLATE: form_template,
        ENTITY_OVERVIEW_TEMPLATE: entity_overview_page,
        APPLICATION_YML_TEMPLATE: application_yml
    }

    filename_function = switcher.get(template_name, lambda: "Invalid arg")
    filename = filename_function(directory, render_args['app_name'], entity_name)
    write_to_file(template.render(render_args), filename)
