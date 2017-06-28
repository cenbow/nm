( function( $ ){
	var $body = $( "body" );
	
	function respond() {
		if ( $body.length == 0 ) {
			$body = $( "body" )
		}
		var width =  $( window ).width();
		if ( width >= 1200 ) {
			$body.removeClass( "respond-sm respond-md" ).addClass( "respond-lg" );
			console.log( $body.attr( "class" ) );
		} else if ( width >= 992 ) {
			$body.removeClass( "respond-sm respond-lg" ).addClass( "respond-md" );
		} else if ( width >= 768 ) {
			$body.removeClass( "respond-md respond-lg" ).addClass( "respond-sm" );
		} else {
			$body.removeClass( "respond-md-sm respond-md respond-lg" );
		}
	}
	
	$( window ).bind( "resize", function( event ) {
		respond();
	} );
	
	$( function() {
		respond();
	} );
	
	if ( $body.length != 0 ) {
		respond();
	}
} )( jQuery );