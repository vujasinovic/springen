from springlang.utilities.classes import SimpleType, Property


def parse_entities(entities):
    types = []
    unknown_type_properties = []

    for entity in entities:
        entity_type = SimpleType(entity.name, is_user_type=True, belongsTo=entity.belongsTo, parent=entity.parent)

        for property in entity.properties:
            data_type = property.type
            if isinstance(data_type, SimpleType):
                p = Property(entity_type, property.name, data_type)
                entity_type.properties.append(p)
            else:
                p = Property(entity_type, property.name, SimpleType(data_type.name))
                unknown_type_properties.append(p)
                entity_type.properties.append(p)
            p.list = property.__class__.__name__ == 'ListProperty'
            p.display = property.__class__.__name__ == 'DisplayProperty'

        types.append(entity_type)

    for property in unknown_type_properties:
        for user_type in types:
            if user_type.name == property.type.name:
                property.type = user_type

    for type in types:
        for property in type.properties:
            if property.list and property.type.is_user_type:
                related_property = Property(property.type, type.name.lower(), type, parent_relation=property)
                property.mapped_by = related_property
                property.type.properties.append(related_property)

    return types
