define([ 'jquery','ace','editor','tokenfield'], function($) {
    $(function() {
        $('#markdown').markdownEditor();
        $('#tags').tokenfield()
    });
});