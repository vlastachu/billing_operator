<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>login page</title>
    <link rel="stylesheet" type="text/css" href="css/auth.css">
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
</head>

<body class=" bgcolor-4">
    <div id="loginBox"  class="content vertical-center">
        <form action="j_security_check" class="container" method="POST" style="max-width: 1200px">
            <h2><%= request.getParameter("authmessage") %></h2>
            <div class="input input--akira">
                <input class="input__field input__field--akira" type="text" id="j_username" name="j_username"/>
                <label class="input__label input__label--akira" for="j_username">
                    <span class="input__label-content input__label-content--akira">User Name</span>
                </label>
            </div>
            <div class="input input--akira">
                <input class="input__field input__field--akira" type="password" id="j_password"  name="j_password"/>
                <label class="input__label input__label--akira" for="j_password">
                    <span class="input__label-content input__label-content--akira">Password</span>
                </label>
            </div>
            <div class="input input--akira">
                <input type="submit" class="input__button" name="log-in" value="Log in">
            </div>
            <p><a href="registration">Registration</a></p>
        </form>
    </div>
    <script>
        /*!
         * classie - class helper functions
         * from bonzo https://github.com/ded/bonzo
         *
         * classie.has( elem, 'my-class' ) -> true/false
         * classie.add( elem, 'my-new-class' )
         * classie.remove( elem, 'my-unwanted-class' )
         * classie.toggle( elem, 'my-class' )
         */

        /*jshint browser: true, strict: true, undef: true */
        /*global define: false */

        ( function( window ) {

            'use strict';

// class helper functions from bonzo https://github.com/ded/bonzo

            function classReg( className ) {
                return new RegExp("(^|\\s+)" + className + "(\\s+|$)");
            }

// classList support for class management
// altho to be fair, the api sucks because it won't accept multiple classes at once
            var hasClass, addClass, removeClass;

            if ( 'classList' in document.documentElement ) {
                hasClass = function( elem, c ) {
                    return elem.classList.contains( c );
                };
                addClass = function( elem, c ) {
                    elem.classList.add( c );
                };
                removeClass = function( elem, c ) {
                    elem.classList.remove( c );
                };
            }
            else {
                hasClass = function( elem, c ) {
                    return classReg( c ).test( elem.className );
                };
                addClass = function( elem, c ) {
                    if ( !hasClass( elem, c ) ) {
                        elem.className = elem.className + ' ' + c;
                    }
                };
                removeClass = function( elem, c ) {
                    elem.className = elem.className.replace( classReg( c ), ' ' );
                };
            }

            function toggleClass( elem, c ) {
                var fn = hasClass( elem, c ) ? removeClass : addClass;
                fn( elem, c );
            }

            var classie = {
                // full names
                hasClass: hasClass,
                addClass: addClass,
                removeClass: removeClass,
                toggleClass: toggleClass,
                // short names
                has: hasClass,
                add: addClass,
                remove: removeClass,
                toggle: toggleClass
            };

// transport
            if ( typeof define === 'function' && define.amd ) {
                // AMD
                define( classie );
            } else {
                // browser global
                window.classie = classie;
            }

        })( window );
    </script>
    <script>
        (function() {
            // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
            if (!String.prototype.trim) {
                (function() {
                    // Make sure we trim BOM and NBSP
                    var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                    String.prototype.trim = function() {
                        return this.replace(rtrim, '');
                    };
                })();
            }
            [].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
                // in case the input is already filled..
                if( inputEl.value.trim() !== '' ) {
                    classie.add( inputEl.parentNode, 'input--filled' );
                }
                // events:
                inputEl.addEventListener( 'focus', onInputFocus );
                inputEl.addEventListener( 'blur', onInputBlur );
            } );
            function onInputFocus( ev ) {
                classie.add( ev.target.parentNode, 'input--filled' );
            }
            function onInputBlur( ev ) {
                if( ev.target.value.trim() === '' ) {
                    classie.remove( ev.target.parentNode, 'input--filled' );
                }
            }
        })();
    </script>
</body>

</html>
