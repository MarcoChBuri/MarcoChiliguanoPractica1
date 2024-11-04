from flask import Blueprint, render_template, request, redirect, url_for, jsonify
import requests
from datetime import datetime

router = Blueprint('router', __name__)
URL_LINKEDLISTS = 'http://localhost:8080/myapp/'

def fetch_data(url):
    """Función para obtener datos de una URL con manejo de errores."""
    try:
        response = requests.get(url)
        response.raise_for_status()
        return response.json().get('data', []), None  # Devolver datos y None como error
    except requests.exceptions.RequestException as e:
        return [], f"Error al obtener datos de {url}: {e}"  # Devolver lista vacía y el error

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

@router.route('/')
def home():
    return render_template('index.html')

@router.route('/proyectos/')
def lista_Proyectos():
    data, error = fetch_data(URL_LINKEDLISTS + 'proyecto')
    if error:
        return error, 400

    if data is None:
        data = []

    for proyecto in data:
        # Convertir las fechas de timestamp a un formato legible
        proyecto['fechaInicio'] = format_date(proyecto['fechaInicio'])
        proyecto['fechaFin'] = format_date(proyecto['fechaFin'])

    return render_template('proyectos.html', proyectos=data, mensaje="No hay proyectos creados." if not data else "")



@router.route('/proyecto/crear', methods=['GET', 'POST'])
def crearProyecto():
    if request.method == 'POST':
        data = {
            "nombre": request.form['nombre'],
            "tiempoVida": int(request.form['tiempoVida']),
            "fechaInicio": int(datetime.strptime(request.form['fechaInicio'], '%Y-%m-%d').timestamp() * 1000),
            "fechaFin": int(datetime.strptime(request.form['fechaFin'], '%Y-%m-%d').timestamp() * 1000),
        }
        success, error = post_data(URL_LINKEDLISTS + 'proyecto', data)
        if success:
            return redirect(url_for('router.lista_Proyectos'))
        return error, 400
    return render_template('crearProyecto.html')



@router.route('/proyectos/editar/<int:idProyecto>/', methods=['GET', 'POST'])
def actualizarProyecto(idProyecto):
    if request.method == 'POST':
        data = {
            "nombre": request.form['nombre'],
            "tiempoVida": int(request.form['tiempoVida']),
            "fechaInicio": int(datetime.strptime(request.form['fechaInicio'], '%Y-%m-%d').timestamp() * 1000),
            "fechaFin": int(datetime.strptime(request.form['fechaFin'], '%Y-%m-%d').timestamp() * 1000),

        }
        success, error = put_data(URL_LINKEDLISTS + f'proyecto/{idProyecto}', data)
        if success:
            return redirect(url_for('router.lista_Proyectos'))
        return error, 400

    data, error = fetch_data(URL_LINKEDLISTS + f'proyecto/{idProyecto}')
    if error:
        return error, 400
    if data:
        data['fechaInicio'] = format_date(data['fechaInicio'])
        data['fechaFin'] = format_date(data['fechaFin'])
        return render_template('crearProyecto.html', proyecto=data)
    return "Proyecto no encontrado", 404
####################################################
@router.route('/inversionistas/')
def lista_Inversionista():
    data, error = fetch_data(URL_LINKEDLISTS + 'inversionistas')
    if error:
        return error, 400
    return render_template('inversionista.html', inversionistas=data, mensaje="No hay inversionistas creados." if not data else "")




@router.route('/inversionistas/crear', methods=['GET', 'POST'])
def crearInversionista():
    if request.method == 'POST':
        data = {
            "name": request.form['name'],
            "lastName": request.form['lastName'],
            "dni": request.form['dni'],
            "identification": request.form['identification'],
            "direction": request.form['direction']
        }
        success, error = post_data(URL_LINKEDLISTS + 'inversionistas', data)
        if success:
            return redirect(url_for('router.lista_Inversionista'))
        return error, 400
            
    dni_options, error = fetch_data(URL_LINKEDLISTS + 'inversionistas/dni')
    dni_options = dni_options or []
    return render_template('crearInversionista.html', dni_options=dni_options)

@router.route('/dni/opciones', methods=['GET'])
def obtener_opciones_dni():
    opciones_dni, error = fetch_data(URL_LINKEDLISTS + 'inversionistas/dni')
    if error:
        return error, 400
    return jsonify(opciones_dni)

@router.route('/inversionistas/editar/<int:idInversionista>/', methods=['GET', 'POST'])
def editarInversionista(idInversionista):
    if request.method == 'POST':
        data = {
            "name": request.form['name'],
            "lastName": request.form['lastName'],
            "dni": request.form['dni'],
            "identification": request.form['identification'],
            "direction": request.form['direction']
        }
        success, error = put_data(URL_LINKEDLISTS + f'inversionistas/{idInversionista}', data)
        if success:
            return redirect(url_for('router.lista_Inversionista'))
        return error, 400

    # Obtener datos del inversionista a editar
    inversionista, error = fetch_data(URL_LINKEDLISTS + f'inversionistas/{idInversionista}')
    if error:
        return error, 400

    # Obtener opciones de DNI para el formulario
    dni_options, error = fetch_data(URL_LINKEDLISTS + 'inversionistas/dni')
    if error:
        return error, 400
    
    if inversionista:
        return render_template('crearInversionista.html', inversionista=inversionista, dni_options=dni_options)
    
    return "Inversionista no encontrado", 404 
########################################################3
@router.route('/proyectos/detalle')
def detalle_proyectos():
    data, error = fetch_data(URL_LINKEDLISTS + "proyecto/detalle")
    if error:
        return error, 400
    return render_template('detallesProyectos.html', list=data)

@router.route('/proyectos/detalle/crear', methods=['GET', 'POST'])
def add_detalle_proyecto():
    if request.method == 'POST':
        data = {
            'proyecto': request.form['proyecto'],
            'inversionTotal': request.form['inversionTotal'],
        }
        success, error = post_data(URL_LINKEDLISTS + "proyecto/detalle", data)
        if success:
            return redirect(url_for('router.detalle_proyectos'))
        return error, 400
    return render_template('crearDetalleProyecto.html')
###############################################################################3





#######################################################3


@router.route('/inversionistas/detalle')
def detalle_inversionistas():
    data, error = fetch_data(URL_LINKEDLISTS + "inversionista/detalle")
    if error:
        return error, 400
    return render_template('detallesInversionistas.html', list=data)

@router.route('/inversionistas/detalle/crear', methods=['GET', 'POST'])
def add_detalle_inversionista():
    if request.method == 'POST':
        data = {
            'inversionista': request.form['inversionista'],
            'montoInvertido': request.form['montoInvertido'],
        }
        success, error = post_data(URL_LINKEDLISTS + "inversionista/detalle", data)
        if success:
            return redirect(url_for('router.detalle_inversionistas'))
        return error, 400
    return render_template('crearDetalleInversionista.html')