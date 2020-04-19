def entity_package_path(entity):
    if entity.belongsTo is None:
        default_path = 'rs.ftn'
        return default_path
    package_names_reversed = entity_package_uri(entity.parent, entity.belongsTo.package)
    package_names = package_names_reversed[::-1]
    full_path = '.'.join(package_names)
    return full_path


def entity_package_uri(entity_model, current_package, package_names=[]):
    package_names.append(current_package.name)
    if current_package.parent is None or current_package.parent is entity_model:
        return package_names
    else:
        return entity_package_uri(entity_model, current_package.parent, package_names)

