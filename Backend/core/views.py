from rest_framework import viewsets
from .models import Patient, SymptomReport
from .serializers import PatientSerializer, SymptomReportSerializer

class PatientViewSet(viewsets.ModelViewSet):
    queryset = Patient.objects.all()
    serializer_class = PatientSerializer

class SymptomReportViewSet(viewsets.ModelViewSet):
    queryset = SymptomReport.objects.all()
    serializer_class = SymptomReportSerializer
