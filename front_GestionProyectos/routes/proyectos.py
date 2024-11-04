# routers/proyectos.py
from flask import Blueprint, render_template

router_proyectos = Blueprint('proyectos', __name__)

def fetch_data(url):
    """Función para obtener datos de una URL con manejo de errores."""
    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.json().get('data', []), None  # Devolver datos y None como error
    except requests.exceptions.RequestException as e:
        return None, f"Error al obtener datos de {url}: {e}"  # Devolver None y el error

def post_data(url, data):
    """Función para enviar datos a una URL con manejo de errores."""
    try:
        response = requests.post(url, json=data)
        response.raise_for_status()
        return True, None
    except requests.exceptions.RequestException as e:
        return False, f"Error al enviar datos a {url}: {e}"

def put_data(url, data):
    """Función para actualizar datos en una URL con manejo de errores."""
    try:
        response = requests.put(url, json=data)
        response.raise_for_status()
        return True, None
    except requests.exceptions.RequestException as e:
        return False, f"Error al actualizar datos en {url}: {e}"

def format_date(timestamp):
    """Función para formatear la fecha de un timestamp."""
    return datetime.fromtimestamp(timestamp / 1000).strftime('%Y-%m-%d')

URL_LINKEDLISTS = 'http://localhost:8080/myapp/'  # Asegúrate de que esta URL sea correcta

@router_proyectos.route('/detalles')
def detalle_proyectos():
    data, error = fetch_data(URL_LINKEDLISTS + 'proyectoDetalle')
    if error:
        return error, 400

    return render_template('detallesProyectos.html', proyectos=data, mensaje="No hay proyectos creados." if not data else "")

@router_proyectos.route('/detalles/<int:idProyecto>')
def detalle_proyecto(idProyecto):
    data, error = fetch_data(URL_LINKEDLISTS + f'proyecto/{idProyecto}')  # Asegúrate de que esta URL sea correcta
    if error:
        return error, 400

    if not data:
        return "Proyecto no encontrado", 404

    # Formatear las fechas si es necesario
    data['fechaInicio'] = format_date(data['fechaInicio'])
    data['fechaFin'] = format_date(data['fechaFin'])

    return render_template('detallesProyectos.html', proyecto=data)
