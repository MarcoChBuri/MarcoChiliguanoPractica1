# routers/inversionistas.py
from flask import Blueprint, render_template
from .fetch_data import fetch_data  # Importa tu función fetch_data

router_inversionistas = Blueprint('inversionistas', __name__)

@router_inversionistas.route('/detalles')
def detalle_inversionistas():
    data, error = fetch_data(URL_LINKEDLISTS + 'inversionistaDetalle')
    if error:
        return error, 400

    return render_template('detallesInversionistas.html', inversionistas=data, mensaje="No hay inversionistas creados." if not data else "")
