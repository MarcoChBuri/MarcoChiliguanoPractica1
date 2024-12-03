from flask import Blueprint, render_template, request, redirect, url_for, jsonify
import requests
import json
router = Blueprint('router', __name__)
BASE_URL = 'http://localhost:8080/myapp/'

@router.route('/')
def home():
    return render_template('home.html')
@router.route('/page1')
def page1():
    return "This is Page 1"

@router.route('/page2')
def page2():
    return "This is Page 2"

