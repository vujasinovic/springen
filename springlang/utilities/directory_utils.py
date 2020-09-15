import os
from os.path import join


def create_directory(name, folder):
    directory = join(folder, name)

    if not os.path.exists(directory):
        os.makedirs(directory)

    return directory
