from django.contrib import admin
from django.urls import path, include
from django.http import JsonResponse

def home(request):
    return JsonResponse({"message": "Welcome to MediBot Afrika API"})

urlpatterns = [
    path('', home),
    path('admin/', admin.site.urls),
    path('api/', include('core.urls')),  # 👈 Important
]
