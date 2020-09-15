from setuptools import setup, find_packages

PACKAGE_NAME = 'SpringLang'
VERSION = "0.1.0"
AUTHOR = 'JSD TEAM 14'
AUTHOR_EMAIL = 'luka.ra109@uns.ac.rs'
DESCRIPTION = 'language for springboot app generation'
KEYWORDS = "textX DSL python domain specific languages entity"
LICENSE = "MIT"
URL = 'https://github.com/vujasinovic/springen'

setup(
    name=PACKAGE_NAME,
    version=VERSION,
    description=DESCRIPTION,
    url=URL,
    author=AUTHOR,
    author_email=AUTHOR_EMAIL,
    keywords=KEYWORDS,
    license=LICENSE,
    packages=find_packages(),
    include_package_data=True,
    package_data={"": ["*.tx", "template/commons/*", "template/*", "template/jsp/*"]},
    install_requires=["textx", "Jinja2"],
    entry_points={"textx_languages": ["entity = springlang.meta:entity"], "textx_generators": ["EntityGen = springlang:entity_gen"]},
    classifiers=[
        "Development Status :: 2 - Pre-Alpha",
        "Intended Audience :: Developers",
        "Intended Audience :: Information Technology",
        "Topic :: Software Development :: Libraries :: Python Modules",
        "License :: OSI Approved :: MIT License",
        "Operating System :: OS Independent",
        "Programming Language :: Python :: 3 :: Only",
        "Programming Language :: Python :: 3.5",
        "Programming Language :: Python :: 3.6",
        "Programming Language :: Python :: 3.7",
        "Programming Language :: Python :: 3.8",
    ],
)
