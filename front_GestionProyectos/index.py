from app import create_app
from flask import Flask, jsonify

app = create_app()

if __name__ == '__main__':
    print("Iniciando la aplicación Flask...")
    app.run(debug=True, host="0.0.0.0", port=5000)
