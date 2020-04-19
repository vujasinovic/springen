def to_pascalcase(st):
    return st[0].upper() + st[1:]


def to_lowercase(st):
    return st[0].lower() + st[1:]


def to_datetime(value, form='%d.%m.%Y., %H:%M'):
    return value.strftime(form)
