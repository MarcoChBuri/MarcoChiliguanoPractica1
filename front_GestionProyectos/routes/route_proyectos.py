from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

# Definir el Blueprint para las rutas de proyectos
route_proyectos = Blueprint('route_proyectos', __name__)

# URL del API backend (modifica según sea necesario)
URL = 'http://localhost:8080/myapp/proyectos/'

# Ruta para mostrar todos los proyectos
@route_proyectos.route('/proyectos')
def view_proyectos():
    r = requests.get(URL + 'all')
    data = r.json().get('data')
    return render_template('parts/proyectos.html', proyectos=data)

# Ruta para mostrar el formulario de registro de un proyecto
@route_proyectos.route('/proyectos/registro')
def registro_proyecto():
    return render_template('forms/proyectos/registroProyecto.html')

# Ruta para guardar un nuevo proyecto
@route_proyectos.route('/proyectos/registro/save', methods=["POST"])
def save_proyecto():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {
        "nombre": form["nombre"],
        "fechaInicio": form["fechaInicio"],
        "fechaFin": form["fechaFin"],
        "inversion": form["inversion"],
        "tiempoVida": form["tiempoVida"],
        "energia": form["energia"]
    }
    r = requests.post(URL + 'save', data=json.dumps(dataForm), headers=headers)
    data = r.json()
    if r.status_code == 200:
        flash('Proyecto registrado correctamente', category='info')
    else:
        flash(data['data'], category='error')
    return redirect('/proyectos')

# Ruta para mostrar el formulario de edición de un proyecto
@route_proyectos.route('/proyectos/edit/<id>')
def update_proyecto_view(id):
    r = requests.get(URL + 'get/' + id)
    if r.status_code == 200:
        data = r.json()
        return render_template('forms/proyectos/modificarProyecto.html', proyecto=data["data"])
    else:
        flash('No se pudo encontrar el proyecto', category='error')
        return redirect('/proyectos')

# Ruta para actualizar un proyecto
@route_proyectos.route('/proyectos/edit/save', methods=["POST"])
def update_proyecto():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {
        "id": form["id"],
        "nombre": form["nombre"],
        "fechaInicio": form["fechaInicio"],
        "fechaFin": form["fechaFin"],
        "inversion": form["inversion"],
        "tiempoVida": form["tiempoVida"],
        "energia": form["energia"]
    }
    r = requests.post(URL + 'update', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Proyecto actualizado correctamente', category='info')
        return redirect('/proyectos')
    else:
        flash('No se pudo actualizar el proyecto', category='error')
    return redirect('/proyectos')

# Ruta para eliminar un proyecto
@route_proyectos.route('/proyectos/delete/<id>', methods=["POST"])
def delete_proyecto(id):
    headers = {'Content-Type': 'application/json'}
    dataForm = {"id": id}
    r = requests.post(URL + 'delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Proyecto eliminado correctamente', category='info')
        return redirect('/proyectos')
    else:
        flash('No se pudo eliminar el proyecto', category='error')
    return redirect('/proyectos')

# Ruta para ordenar proyectos
@route_proyectos.route('/proyectos/order/<algorithm>/<attribute>/<type>')
def order_proyectos(algorithm, attribute, type):
    url = URL + 'order' + '/' + algorithm + '/' + attribute + '/' + type
    r = requests.get(url)
    print(url)

    data = r.json().get('data')
    if r.status_code == 200:
        
        flash('Proyectos ordenados correctamente', category='info')
        return render_template('parts/proyectos.html', proyectos=data)
    else:
        flash(f'Error al ordenar los proyectos. Código de estado: {r.status_code}', category='error')
        return redirect('/proyectos')

# Ruta para buscar proyectos con búsqueda lineal
@route_proyectos.route('/proyectos/LinearSearch/<attribute>/<value>')
def search_linear(attribute, value):
    url = URL + 'buscar/LinearSearch/'  + attribute + '/' + value
    r = requests.get(url)
    print(url)

    data = r.json().get("data")
    if r.status_code == 200:
        flash('Proyectos encontrados', category='info')
        return render_template('parts/proyectos.html', proyectos=data)
    else:
        flash('No se encontraron resultados con la búsqueda lineal', category='info')
        return redirect('/proyectos')

# Ruta para buscar proyectos con búsqueda binaria
@route_proyectos.route('/proyectos/binarySearch/<attribute>/<value>')
def search_binary(attribute, value):
    url = URL + 'buscar/binarySearch/'  + attribute + '/' + value
    r = requests.get(url)
    print(url)

    data = r.json().get("data")
    if r.status_code == 200:
        flash('Proyectos encontrados', category='info')
        return render_template('parts/proyectos.html', proyectos=data)
    else:
        flash('No se encontraron resultados con la búsqueda binaria', category='info')
        return redirect('/proyectos')
