var gulp = require('gulp'),
    gutil = require('gulp-util'),
    concat = require('gulp-concat'),
    gulpif = require('gulp-if'),
    uglify = require('gulp-uglify'),
    minifyHTML = require('gulp-minify-html'),
    connect = require('gulp-connect'),
    minifyCSS = require('gulp-minify-css')
    less = require('gulp-less'),
    path = require('path'),
    jshint = require('gulp-jshint');

var env,
    jsSources3rd,
    jsSources, 
    cssSources3rd,
    cssSources,
    lessSources,
    watchLessSources,
    htmlSources,
    partialSources,
    fontSources, 
    imageSources, 
    outputDir, 
    jsExt,
    cssExt;

env = process.env.NODE_ENV || 'development';

if(isDevelopment()) {
  outputDir = 'builds/development/';
  jsExt = '.js';
  cssExt = '.css';
} else if(isProduction()) {
  outputDir = 'builds/production/';
  jsExt = '.min.js';
  cssExt = '.min.css';
} else {
  throw new Error('Please specify NODE_ENV as "production".');
}

jsSources3rd = [
  'bower_components/jquery/dist/jquery' + jsExt,
  'bower_components/bootstrap/dist/js/bootstrap' + jsExt,
  'bower_components/lodash/dist/lodash' + jsExt,
  'bower_components/angular/angular' + jsExt,
  'bower_components/restangular/dist/restangular' + jsExt,
  'bower_components/angular-ui-router/release/angular-ui-router' + jsExt,
  'bower_components/angular-bootstrap/ui-bootstrap-tpls' + jsExt,
  'bower_components/angular-grid/build/ng-grid' + jsExt,
  'bower_components/select2/select2' + jsExt,
  // TODO: In production, below src does not have min version
  'bower_components/angular-ui-select2/src/select2.js'
];

// add app script sources here
jsSources = [
  'components/scripts/app.js',
  'components/scripts/config.js',
  'components/scripts/controllers/*.js',
  'components/scripts/services/*.js',
  'components/scripts/filters/*.js',
  'components/scripts/directives/*.js'
];

lessSources = [
  'components/less/bootstrap.less'
];

watchLessSources = [
  'components/less/**/*.less'
];

cssSources3rd = [
  'bower_components/angular-grid/ng-grid' + cssExt,
  // TODO: In production, below src does not have min version
  'bower_components/select2/select2.css'
];

// app css sources here
cssSources = [
  'components/css/*.css'
];

fontSources = [
  'bower_components/bootstrap/dist/fonts/*.*',
  'components/fonts/*.*'
];

imageSources = [
  'components/img/**/*.*'
];

htmlSources = [
  'components/html/**/*.html'
];

partialSources = [
  'components/partials/**/*.html'
];

gulp.task('lint', function() {
  return gulp.src(jsSources)
    .pipe(jshint())
    .pipe(jshint.reporter('default'));
});

gulp.task('js', ['lint'], function() {
  // handle 3rd scripts
  if(jsSources3rd.length > 0) {
    gulp.src(jsSources3rd)
      .pipe(concat('lib.js'))
      .pipe(gulp.dest(outputDir + 'js'));
  }

  // handle app scripts
  if(jsSources.length > 0) {
    gulp.src(jsSources)
      .pipe(concat('script.js'))
      .pipe(gulpif(isProduction(), uglify()))
      .pipe(gulp.dest(outputDir + 'js'))
      .pipe(connect.reload());
  }
});

// mainly for bootstrap now
gulp.task('less', function () {
  gulp.src(lessSources)
    .pipe(less({
      paths: [ path.join(__dirname, 'components', 'less') ]
    }))
    .pipe(gulpif(isProduction(), minifyCSS({keepBreaks:true})))
    .pipe(gulp.dest(outputDir + 'css'))
    .pipe(connect.reload());
});

gulp.task('css', function() {
  // handle 3rd css
  if(cssSources3rd.length > 0) {
    gulp.src(cssSources3rd)
      .pipe(concat('lib.css'))
      .pipe(gulp.dest(outputDir + 'css'));
  }

  // handle app css
  if(cssSources.length > 0) {
    gulp.src(cssSources)
      .pipe(concat('style.css'))
      .pipe(gulpif(isProduction(), minifyCSS({keepBreaks:true})))
      .pipe(gulp.dest(outputDir + 'css'))
      .pipe(connect.reload());
  }
});

// copy the fonts
gulp.task('font', function() {
  if(fontSources.length > 0) {
    gulp.src(fontSources)
      .pipe(gulp.dest(outputDir + 'fonts'))
      .pipe(connect.reload());
  }
});

gulp.task('images', function() {
  gulp.src(imageSources)
    // TODO: may need compression in production mode
    .pipe(gulp.dest(outputDir + 'img'))
    .pipe(connect.reload())
});

gulp.task('html', function() {
  if(htmlSources.length > 0) {
    gulp.src(htmlSources)
      .pipe(gulpif(isProduction(), minifyHTML()))
      .pipe(gulp.dest(outputDir))
      .pipe(connect.reload());
  }
});

gulp.task('partials', function() {
  if(partialSources.length > 0) {
    gulp.src(partialSources)
      .pipe(gulpif(isProduction(), minifyHTML()))
      .pipe(gulp.dest(outputDir + 'partials'))
      .pipe(connect.reload());
  }
});

gulp.task('watch', function() {
  gulp.watch(jsSources, ['js']);
  gulp.watch(watchLessSources, ['less']);
  gulp.watch(cssSources, ['css']);
  gulp.watch(fontSources, ['font']);
  gulp.watch(htmlSources, ['html']);
  gulp.watch(partialSources, ['partials']);
  gulp.watch(imageSources, ['images']);
});

gulp.task('connect', function() {
  connect.server({
    root: outputDir,
    livereload: true
  });
});

gulp.task('default', ['html', 'partials', 'js', 'less', 'css', 'font', 'images', 'connect', 'watch']);

function isDevelopment() {
  return env === 'development';
}

function isProduction() {
  return env === 'production';
}