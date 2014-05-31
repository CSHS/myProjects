from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
from website.models import User, Image
from django.utils import simplejson
# Create your views here.
def first(request):
	#returns first.html
	return render(request,'website/first.html')

def main_page(request):
	#check if session exists
	return render(request, 'website/main.html')

def main(request):
	#user = get_object_or_404(User, email=request.POST['username'])
	try:
		user = User.objects.get(email=request.POST['username'],password=request.POST['password'])
	except (KeyError,User.DoesNotExist):
		return render(request, 'website/first.html', {
			'error': "The email address or password is wrong",
			})
	
	#get all the pictures of this user
	#TODO: upload picture feature -> communicate with backend via POST 
	#try:
	#	pictures = user.image_set.get(pk=user.pk)
	#except 
	request.session['user'] = user.id;
	#/first/ is base url
	return HttpResponseRedirect('/first/main_page/')
	#doing /first/main/main_page is not good cause it thinks it has to do what code is in main and then main_page, so confusing.  so just do /first/main_page
	#return render(request,'website/main.html')

def load_images(request):
	#do the required things to use sessions.
	if request.method == 'GET' and request.is_ajax():
		if request.GET['mode']:
			#json = '{['
			json = ''
			user_id = request.session['user']
			user = User.objects.get(pk = user_id)
			images = user.image_set.all()
			for x in images:
				json+="'file'"+x.image_url
			#json = json.rstrip(",")
			#json+="}"
			print(json)
			json = simplejson.dumps(json)
			#json+=']}'
			#print(json)
			return HttpResponse(json)