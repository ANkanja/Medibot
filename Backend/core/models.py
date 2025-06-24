from django.db import models

class Patient(models.Model):
    name = models.CharField(max_length=100)
    phone = models.CharField(max_length=15, unique=True)
    age = models.IntegerField()
    gender = models.CharField(max_length=10)
    location = models.CharField(max_length=255)
    created_at = models.DateTimeField(auto_now_add=True)

class SymptomReport(models.Model):
    patient = models.ForeignKey(Patient, on_delete=models.CASCADE)
    symptoms = models.TextField()
    diagnosis = models.CharField(max_length=255, blank=True)
    created_at = models.DateTimeField(auto_now_add=True)
