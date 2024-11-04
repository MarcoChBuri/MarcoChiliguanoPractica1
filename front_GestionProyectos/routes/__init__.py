# routes/__init__.py

from flask import Blueprint

router = Blueprint('main', __name__)

from . import route  # Importa el módulo de rutas para que se registren sus rutas
