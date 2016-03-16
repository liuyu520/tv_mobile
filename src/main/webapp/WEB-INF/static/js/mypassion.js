/*
 * Copyright (c) 24/04/2013 MyPassion
 * Author: MyPassion 
 * This file is made for NEWS
*/

	

// ----------------------------------------------------  CONTACT FORM
function submitForm(){
	"use strict";
	var msg;
	$.post('plugin/sendmail.php',$('#contactForm').serialize(), function(msg) {
		$(".alertMessage").html(msg);
	});
	// Hide previous response text
	$(msg).remove();
	// Show response message
	contactform.prepend(msg);
}
jQuery(function(){
	// -----------------------------------------------------  FLEXSLIDER
	jQuery('.flexslider').flexslider({
		animation: 'fade',
		controlNav: false,
		slideshowSpeed: 4000,
		animationDuration: 300
	});	
	
})

jQuery(function() {
    /* var $carousel_first = jQuery('#carousel');
	if($carousel_first.length){
	$carousel_first.carouFredSel({
		width: '100%',
		direction   : "bottom",
		scroll : 400,
		items: {
			visible: '+3'
		},
		auto: {
			items: 1,
			timeoutDuration : 4000
		},
		prev: {
			button: '#prev1',
			items: 1
		},    
		next: {
			button: '#next1',
			items: 1
		}
	});}else{
		if(console.log){
            console.log("#carousel is not found yet");
		}
     }*/

    jQuery('#carousel2').carouFredSel({
		width: '100%',
		direction   : "left",
		scroll : {
	        duration : 800
	    },
		items: {
			visible: 1
		},
		auto: {
			items: 1,
			timeoutDuration : 4000
		},
		prev: {
			button: '#prev2',
			items: 1
		},    
		next: {
			button: '#next2',
			items: 1
		}
	});

    jQuery('#carousel3').carouFredSel({
        width: '100%',
        direction: "left",
        scroll: {
            duration: 800
        },
        items: {
            visible: 1
        },
        auto: {
            items: 1,
            timeoutDuration: 4000
        },
        prev: {
            button: '#prev3',
            items: 1
        },
        next: {
            button: '#next3',
            items: 1
        }
    });


});

jQuery(document).ready(function(){
		
	"use strict";

// -----------------------------------------------------  UI ELEMENTS
	var $accordion22=jQuery( "#accordion" );
	if($accordion22.accordion){
	$accordion22.accordion({
		heightStyle: "content"
	});
	}else{
		if(console.log){
			console.log('#accordion is not found yet.');
		}
	}
	var $tabs_hw=jQuery("#tabs");
	if($tabs_hw.tabs){
	$tabs_hw.tabs();}
	else{
		if(console.log){
			console.log("#tabs is not found yet");
		}
	}
	var $tooltip_hw=jQuery("#tooltip");
	if($tooltip_hw.tooltip){
		$tooltip_hw.tooltip({
		position:{
			my: "center bottom-5",
			at: "center top"	
		}
	});	}
	else{
		if(console.log){
			console.log("#tooltip is not found yet");
		}
	}
	
	
// -----------------------------------------------------  UI ELEMENTS
	var $nav_ul_sf_menuhw=jQuery('#nav ul.sf-menu');
	$nav_ul_sf_menuhw.mobileMenu && $nav_ul_sf_menuhw.mobileMenu({
		defaultText: 'Go to ...',
		className: 'device-menu',
		subMenuDash: '&ndash;'
	});
	
	
// -----------------------------------------------------  NOTIFICATIONS CLOSER
	jQuery('span.closer').click(function(e){
		e.preventDefault();
		jQuery(this).parent('.notifications').stop().slideToggle(500);
	});

// -----------------------------------------------------  NAV SUB MENU(SUPERFISH)
	var $nav_ul_sf_menu=jQuery('#nav ul.sf-menu');
	$nav_ul_sf_menu.superfish && $nav_ul_sf_menu.superfish({
		delay: 250,
		animation: {opacity:'show', height:'show'},
		speed: 'slow',
		autoArrows: true,
		dropShadows: false
	});

// -----------------------------------------------------  TWITTER FEED
	var $tweets_hw=jQuery('#tweets');
	$tweets_hw.tweetable && $tweets_hw.tweetable({
		username: 'envato', 
		time: true,
		rotate: false,
		speed: 4000, 
		limit: 2,
		replies: false,
		position: 'append',
		failed: "Sorry, twitter is currently unavailable for this user.",
		html5: true,
		onComplete:function($ul){
			jQuery('time').timeago();
		}
	});

// -----------------------------------------------------  FLICKR FEED
	var $basicuse_hw=jQuery('#basicuse');
	$basicuse_hw.jflickrfeed && $basicuse_hw.jflickrfeed({
		limit: 8,
		qstrings: {
			id: '52617155@N08'
		},
		itemTemplate: 
		'<li>' +
			'<a href="{{link}}" target="_blank"><img src="{{image_s}}" alt="{{title}}"  /></a>' +
		'</li>'
	});	
	
// -----------------------------------------------------  GOOGLE MAP
	/*var myLatlng = new google.maps.LatLng(-34.397, 150.644);
	var myOptions = {
	  center:myLatlng,
	  zoom:8,
	  mapTypeId: google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map(document.getElementById("map"),  myOptions);
	var marker = new google.maps.Marker({
	  position: myLatlng,
	  map: map,
	  title:"Click Me for more info!"
	});
	
	var infowindow = new google.maps.InfoWindow({});
	
	google.maps.event.addListener(marker, 'click', function() {
		infowindow.setContent("Write here some description"); //sets the content of your global infowindow to string "Tests: "
		infowindow.open(map,marker); //then opens the infowindow at the marker
	});
	marker.setMap(map);*/

});
