class SimpleType(object):
    def __init__(self, name, properties=None, belongsTo=None, is_user_type=False):
        if properties is None:
            properties = []
        self.belongsTo = belongsTo
        self.name = name
        self.properties = properties
        self.is_user_type = is_user_type

    def __str__(self):
        return self.name


class Property(object):
    def __init__(self, parent: SimpleType, name, data_type: SimpleType, list=False, parent_relation=None, dto=False,
                 mapped_by=None, display=None):
        self.parentType = parent
        self.name = name
        self.type = data_type
        self.list = list
        self.relatedProperty = parent_relation
        self.mapped_by = mapped_by
        self.dto = dto
        self.display = display
