from django.shortcuts import render, redirect, get_object_or_404
from .models import Planta
from .forms import PlantaForm


def escolher_categoria(request):
    return render(request, 'EscolherCategoria.html')


def listar_plantas(request, categoria):
    plantas = Planta.objects.filter(categoria=categoria)
    print(plantas)

    return render(request, 'ListarPlantas.html', {'plantas': plantas, 'categoria': categoria})


def nova_planta(request, categoria):
    if request.method == 'POST':
        form = PlantaForm(request.POST)
        if form.is_valid():
            planta = form.save(commit=False)
            planta.categoria = categoria
            planta.save()
            return redirect('RelatorioApp:listar_plantas', categoria=categoria)
    else:
        form = PlantaForm()
    return render(request, 'NovaPlanta.html', {'form': form, 'categoria': categoria})


def editar_planta(request, pk):
    planta = get_object_or_404(Planta, pk=pk)
    if request.method == 'POST':
        form = PlantaForm(request.POST, instance=planta)
        if form.is_valid():
            form.save()
            return redirect('RelatorioApp:listar_plantas', categoria=planta.categoria)
    else:
        form = PlantaForm(instance=planta)
    return render(request, 'NovaPlanta.html', {'form': form, 'categoria': planta.categoria})


def listar_todos(request):
    plantas = Planta.objects.all()
    return render(request, 'ListarTodos.html', {'plantas': plantas})