import os
from os.path import join

from main import this_folder


def create_directory(name):
    directory = join(this_folder, name)

    if not os.path.exists(directory):
        os.makedirs(directory)

    return directory
