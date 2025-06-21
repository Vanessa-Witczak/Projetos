
from django.urls import include, path

from RelatorioApp import views

app_name = 'RelatorioApp'

urlpatterns = [
    path('', views.escolher_categoria, name='escolher_categoria'),
    path('categoria/<str:categoria>/', views.listar_plantas, name='listar_plantas'),
    path('editar/<int:pk>/',views.editar_planta, name='editar_plantas'),
    path('cadastrar/<str:categoria>/',views.nova_planta, name='cadastrar_plantas'),
]