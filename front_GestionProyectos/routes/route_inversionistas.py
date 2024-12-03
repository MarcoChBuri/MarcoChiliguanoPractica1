from flask import Blueprint, render_template, request, redirect, flash
import requests
import json

route_inversionistas = Blueprint('route_inversionistas', __name__)
BASE = 'http://localhost:8080/myapp/inversionistas'


# Muestra todos los inversionistas
# Muestra todos los inversionistas con posibilidad de ordenar
@route_inversionistas.route('/inversionistas')
def lista_inversionistas():
    try:
        # Leer parámetros de ordenación de la URL
        algorithm = request.args.get('algorithm', 'default')  # Por ejemplo, 'quicksort'
        attribute = request.args.get('attribute', 'nombre')  # Atributo por el cual ordenar, ejemplo 'nombre'
        type = int(request.args.get('type', 1))  # Tipo de orden (1 para ascendente, 0 para descendente)
        
        # Realizar la petición a la API de ordenamiento
        r = requests.get(f"{BASE}/order/{algorithm}/{attribute}/{type}")
        data = r.json().get('data', [])
        
        return render_template('parts/inversionistas.html', inversionistas=data)
    except Exception as e:
        flash(f'Error al obtener los inversionistas: {e}', 'error')
        return render_template('parts/inversionistas.html', inversionistas=[])

# Formulario de registro para los inversionistas
@route_inversionistas.route('/inversionistas/registro')
def registro_inversionistas():
    return render_template('forms/inversionistas/registroinversionistas.html')

# Enviar datos del formulario de registro hacia el backend
@route_inversionistas.route('/inversionistas/registro/save', methods=["POST"])
def save_inversionistas():
    try:
        headers = {'Content-Type': 'application/json'}
        form = request.form
        data_form = {
            "nombre": form.get("nombre"),
            "apellido": form.get("apellido"),
            "email": form.get("email"),
            "tipo": form.get("tipo"),
            "identificacion": form.get("identificacion")
        }
        r = requests.post(f"{BASE}/save", data=json.dumps(data_form), headers=headers)
        if r.status_code == 200:
            flash('Inversionista registrado correctamente', 'success')
        else:
            message = r.json().get('data', 'No se pudo registrar al inversionista')
            flash(message, 'error')
        return redirect('/inversionistas')
    except Exception as e:
        flash(f'Error inesperado: {e}', 'error')
        return redirect('/inversionistas')

# Ruta para editar un inversionista específico
@route_inversionistas.route('/inversionistas/edit/<id>')
def update_inversionistas_view(id):
    try:
        r = requests.get(f"{BASE}/get/{id}")
        if r.status_code == 200:
            data = r.json().get('data')
            return render_template('forms/inversionistas/modificarInversionista.html', inversionista=data)
        else:
            message = r.json().get('data', 'Error al obtener el inversionista')
            flash(message, 'error')
            return redirect('/inversionistas')
    except Exception as e:
        flash(f'Error inesperado: {e}', 'error')
        return redirect('/inversionistas')

# Enviar datos del formulario de edición hacia el backend
@route_inversionistas.route('/inversionistas/update', methods=["POST"])
def update_inversionistas():
    try:
        headers = {'Content-Type': 'application/json'}
        form = request.form
        data_form = {
            "id": form.get("id"),
            "nombre": form.get("nombre"),
            "apellido": form.get("apellido"),
            "email": form.get("email"),
            "tipo": form.get("tipo"),
            "identificacion": form.get("identificacion")
        }
        r = requests.post(f"{BASE}/update", data=json.dumps(data_form), headers=headers)
        if r.status_code == 200:
            flash('Inversionista actualizado correctamente', 'success')
        else:
            message = r.json().get('data', 'No se pudo actualizar el inversionista')
            flash(message, 'error')
        return redirect('/inversionistas')
    except Exception as e:
        flash(f'Error inesperado: {e}', 'error')
        return redirect('/inversionistas')

# Ruta para eliminar un inversionista específico
@route_inversionistas.route('/inversionistas/delete', methods=["POST"])
def delete_inversionistas():
    try:
        headers = {'Content-Type': 'application/json'}
        form = request.form
        data_form = {"id": form.get("id")}
        r = requests.post(f"{BASE}/delete", data=json.dumps(data_form), headers=headers)
        if r.status_code == 200:
            flash('Inversionista eliminado correctamente', 'success')
        else:
            message = r.json().get('data', 'No se pudo eliminar el inversionista')
            flash(message, 'error')
        return redirect('/inversionistas')
    except Exception as e:
        flash(f'Error inesperado: {e}', 'error')
        return redirect('/inversionistas')
