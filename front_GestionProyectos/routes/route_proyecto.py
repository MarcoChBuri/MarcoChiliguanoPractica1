from flask import Blueprint, render_template, request, redirect, url_for, jsonify
import requests
import json
route_proyecto = Blueprint('route_proyecto', __name__)