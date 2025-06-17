
from django.urls import include, path

from RelatorioApp import views

urlpatterns = [
    path('categoria/<str:categoria>/', views.listar_plantas, name='listar_plantas'),
    path('categoria/<str:categoria>/nova/', views.nova_planta, name='NovaPlanta'),
    path('editar/<int:pk>/', views.listar_plantas, name='EditarPlanta'),
    path('cadastros/', views.listar_todos, name='ListarTodos'),

]