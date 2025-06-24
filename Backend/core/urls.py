from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import PatientViewSet, SymptomReportViewSet

router = DefaultRouter()
router.register(r'patients', PatientViewSet)
router.register(r'symptoms', SymptomReportViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
