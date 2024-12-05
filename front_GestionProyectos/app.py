from flask import Flask

def create_app():
    app = Flask(__name__, instance_relative_config=False)
    app.secret_key = 'tu_clave_secreta_aqui'
    with app.app_context():
        from routes.route import router
        from routes.route_inversionistas import route_inversionistas
        from routes.route_proyectos import route_proyectos

        app.register_blueprint(router)
        app.register_blueprint(route_inversionistas)
        app.register_blueprint(route_proyectos)

    return app

