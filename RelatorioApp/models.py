from django.db import models

class Planta(models.Model) :
    CATEGORIAS = [
        ("leguminosa" , "Leguminosa"),
        ("hortaliça" , "Hortaliça"),
        ("fruta" , " Fruta"),
    ]
    categoria =models.CharField(max_length= 20, choices = CATEGORIAS)
    nome =models.CharField(max_length= 100)
    Data_Do_Plantio = models.CharField(max_length=100)
    Adubo = models.CharField(max_length=100)
    Fase = models.CharField(max_length=100)
    Observacao = models.TextField(blank= True)

    def descricao(self):
        return f"{self.nome} ({self.categoria})"