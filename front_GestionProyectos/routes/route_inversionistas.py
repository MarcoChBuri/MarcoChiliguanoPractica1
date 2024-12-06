from flask import Blueprint, json, render_template, request, redirect, url_for, flash
import requests

route_inversionistas = Blueprint('route_inversionistas', __name__)

URL = 'http://localhost:8080/myapp/inversionistas/'

@route_inversionistas.route('/inversionistas')
def view_inversionistas():
    r = requests.get(URL + 'all')
    print(r.json())  
    data = r.json().get('data')
    return render_template('parts/inversionistas.html', inversionistas=data)

@route_inversionistas.route('/inversionistas/registro')
def registro_inversionista():
    return render_template('forms/inversionistas/registroinversionistas.html')

@route_inversionistas.route('/inversionistas/registro/save', methods=["POST"])
def save_inversionista():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {
        "nombre": form["nombre"],
        "apellido": form["apellido"],
        "email": form["email"],
        "Identification": form["Identification"]
    }
    r = requests.post(URL + 'save', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Inversionista registrado correctamente', category='info')
    else:
        flash('No se ha podido registrar al inversionista', category='error')
    return redirect('/inversionistas')

@route_inversionistas.route('/inversionistas/edit/<id>')
def update_inversionista_view(id):
    r = requests.get(URL + 'get/' + id)
    if r.status_code == 200:
        data = r.json()
        return render_template('forms/inversionistas/modificarInversionista.html', inversionista=data["data"])
    else:
        flash('No se pudo encontrar el inversionista', category='error')
        return redirect('/inversionistas')

@route_inversionistas.route('/inversionistas/edit/save', methods=["POST"])
def update_inversionista():
    headers = {'Content-Type': 'application/json'}
    form = request.form
    dataForm = {
        "id": form["id"],
        "nombre": form["nombre"],
        "apellido": form["apellido"],
        "email": form["email"],
        "Identification": form["Identification"]

    }
    r = requests.post(URL + 'update', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Inversionista actualizado correctamente', category='info')
        return redirect('/inversionistas')
    else:
        flash('No se pudo actualizar el inversionista', category='error')
    return redirect('/inversionistas')

@route_inversionistas.route('/inversionistas/delete/<id>', methods=["POST"])
def delete_inversionista(id):
    headers = {'Content-Type': 'application/json'}
    dataForm = {"id": id}
    r = requests.post(URL + 'delete', data=json.dumps(dataForm), headers=headers)

    if r.status_code == 200:
        flash('Inversionista eliminado correctamente', category='info')
        return redirect('/inversionistas')
    else:
        flash('No se pudo eliminar el inversionista', category='error')
    return redirect('/inversionistas')

@route_inversionistas.route('/inversionistas/order/<algorithm>/<attribute>/<type>')
def order_inversionistas(algorithm, attribute, type):
    url = URL + 'order' + '/' + algorithm + '/' + attribute + '/' + type
    r = requests.get(url)
    print(url)

    data = r.json().get('data')
    
    if r.status_code == 200:
        flash('Inversionistas ordenados', category='info')
        print('si funca')
        return render_template('parts/inversionistas.html', inversionistas=data)
    else:
        flash(f'Error al ordenar los inversionistas. Código de estado:', category='error')
        return redirect('/inversionistas')

@route_inversionistas.route('/inversionistas/LinearSearch/<attribute>/<value>')
def search_linear(attribute, value):
    url = URL + 'buscar/LinearSearch/'  + attribute + '/' + value
    r = requests.get(url)
    print(url)
    print(r.json())  

    data = r.json().get("data")
    if r.status_code == 200:
        flash('Inversioinversionistasnistas encontrados', category='info')
        return render_template('parts/inversionistas.html', inversionistas=data)
    else:
        flash('No se encontraron resultados con la búsqueda lineal', category='info')
        return redirect('/inversionistas')

@route_inversionistas.route('/inversionistas/binarySearch/<attribute>/<value>')
def search_binary(attribute, value):
    url = URL + 'buscar/binarySearch/'  + attribute + '/' + value
    r = requests.get(url)
    print(url)

    data = r.json().get("data")
    if r.status_code == 200:
        flash('Inversioinversionistasnistas encontrados', category='info')
        return render_template('parts/inversionistas.html', inversionistas=data)
    else:
        flash('No se encontraron resultados con la búsqueda lineal', category='info')
        return redirect('/inversionistas')


