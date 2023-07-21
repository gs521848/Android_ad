var _afg = {
  adsManager: null,
  adsLoader: null,
  adDisplayContainer: null,
  videoContent: null,
  intervalTimer: null,
  adContainer: null,
  timeoutTimer: null,
  timeCounter: 15,
  adObject: null,
  iftickTimer: 0,
  mainContainer: null,
  startTime: new Date,
  _adload: null,
  curTime: 0,
  adlength: 65,
  start: function() {
    if ((1 * new Date - _afg.curTime < 50000) && !isRewardAd) {
      console.log('Exceed maximum display times within 50 second');
      adCallBack && adCallBack({code: -1});
    } else {
      window.onShowAfc(adCallBack);
      _afg.curTime = 1 * new Date;
      typeof _clog !== 'undefined' && _clog.sendLog('ad_req');
      return;
      _afg.showadsContainer();
      // afg_loading_show();
      _afg.videoContent = $('#videoElement').get(0);
      _afg.adContainer = $('#adContainer');
      _afg.adContainer.width($(window).width());
      _afg.adContainer.height($(window).height() - _afg.adlength);
      _afg.requestAds();
      $(window).bind('resize', _afg.correctPositions);
    }
  },
  showGame: function() {
    // $('#adsContainer').hide();
    $('#afgContainer').hide();
    // afg_loading_hide();
  },
  correctPositions: function() {
    console.log('correctPositions..');
    if (_afg.adObject && _afg.adsManager) {
      if (_afg.adObject.isLinear()) {
        _afg.adContainer.height($(window).height() - _afg.adlength);
        _afg.adsManager.resize($(window).width(),
            $(window).height() - _afg.adlength, google.ima.ViewMode.NORMAL);
      } else {
        // $('#adsContainer').css({'margin-top': 0});
        // $('#adsContainer').height($(window).height() - _afg.adlength);
        _afg.adContainer.height($(window).height() - _afg.adlength);
        _afg.adsManager.resize(_afg.adObject.getWidth(),
            _afg.adObject.getHeight(), google.ima.ViewMode.NORMAL);
        var PaddingLeft = ($(window).width() - _afg.adObject.getWidth()) / 2;
        if (PaddingLeft < 0) PaddingLeft = 0;
        $('#adContainer').css({'padding-left': PaddingLeft});
      }
    }
  },
  requestAds: function() {
    _afg.adDisplayContainer = new google.ima.AdDisplayContainer(
        _afg.adContainer.get(0));
    _afg.adDisplayContainer.initialize();
    _afg.adsLoader = new google.ima.AdsLoader(_afg.adDisplayContainer);
    _afg.adsLoader.addEventListener(
        google.ima.AdsManagerLoadedEvent.Type.ADS_MANAGER_LOADED,
        _afg.onAdsManagerLoaded, !1);
    _afg.adsLoader.addEventListener(google.ima.AdErrorEvent.Type.AD_ERROR,
        _afg.events.onAdError, !1);
    var e = new google.ima.AdsRequest;
    e.adTagUrl = AFGURL;
    e.forceNonLinearFullSlot = true;
    if (1 == 2) {
      e.linearAdSlotWidth = $(window).width();
      e.linearAdSlotHeight = $(window).height() - _afg.adlength;
    } else {
      e.nonLinearAdSlotWidth = _afg.adContainer.width();
      e.nonLinearAdSlotHeight = _afg.adContainer.height();
    }
    _afg.adsLoader.requestAds(e);
    typeof _clog !== 'undefined' && _clog.sendLog('ad_req');// collectionLog
  },
  onAdsManagerLoaded: function(e) {
    _afg.adsManager = e.getAdsManager(_afg.videoContent);
    _afg.adsManager.addEventListener(google.ima.AdErrorEvent.Type.AD_ERROR,
        _afg.events.onAdError);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.USER_CLOSE,
        _afg.events.onUserClose);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.LOADED,
        _afg.events.onAdEvent);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.STARTED,
        _afg.events.onAdEvent);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.COMPLETE,
        _afg.events.onAdEvent);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.SKIPPED,
        _afg.events.onAdEvent);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.CLICK,
        _afg.events.onAdEvent);
    _afg.adsManager.addEventListener(google.ima.AdEvent.Type.ALL_ADS_COMPLETED,
        _afg.events.onUserClose);
    _afg.adsManager.addEventListener(
        google.ima.AdEvent.Type.CONTENT_RESUME_REQUESTED,
        _afg.events.onUserClose);
    try {
      _afg.adsManager.init(_afg.adContainer.width(), _afg.adContainer.height(),
          google.ima.ViewMode.NORMAL);
      _afg.adsManager.start();
    } catch (i) {
      _afg.showGame();
    }
  },
  events: {
    onUserClose: function() {
      _afg.showGame();
    }, onAdEvent: function(e) {
      var i = e.getAd();
      switch (e.type) {
        case google.ima.AdEvent.Type.LOADED:
          console.log('google.ima.AdEvent.Type.LOADED');
          _afg.adObject = i, _afg.correctPositions();
          // afg_loading_hide();
          $('#afgContainer').show();
          break;
        case google.ima.AdEvent.Type.STARTED:
          _afg.curTime = 1 * new Date;
          console.log('google.ima.AdEvent.Type.STARTED');
          _afg.iftickTimer = 0;
          typeof _clog !== 'undefined' && _clog.sendLog('ad_show');// collectionLog
          break;
        case google.ima.AdEvent.Type.COMPLETE:
          console.log('google.ima.AdEvent.Type.COMPLETE');
          _afg.showGame();
          adCallBack && adCallBack({code: 1});
          break;
        case google.ima.AdEvent.Type.SKIPPED:
          adCallBack && adCallBack({code: -1});
          _afg.showGame();
          break;
        case google.ima.AdEvent.Type.CLICK:
          _afg.showGame();
      }
    }, onAdError: function(e) {
      // adCallBack && adCallBack({code: -1});
      window.onShowAfc(adCallBack);
      _afg.curTime = 1 * new Date;
      typeof _clog !== 'undefined' && _clog.sendLog('ad_req');
      console.log(e);
      _afg.iftickTimer = 0;
      _afg.showGame();
    },
  },
  tickTimer: function() {
    if (_afg.iftickTimer == 1) {
      _afg.timeoutTimer = setTimeout(function() {
        _afg.timeCounter--;
        if (_afg.timeCounter == 0) {
          _afg.showGame();
          _afg.timeCounter = 15;
          clearTimeout(_afg.timeoutTimer);
        } else {
          _afg.tickTimer();
        }
      }, 1e3);
    } else {
      _afg.timeCounter = 15;
      clearTimeout(_afg.timeoutTimer);
    }
  },
  logPageClosedWhileAd: function() {
  },
  showadsContainer: function() {
    _afg.iftickTimer = 1;
    _afg.tickTimer();
    console.log(' showadsContainer: function () ');
    $('#adContainer').empty();
    // $('#adsContainer').show();
  },
  start_afg() {
    try {
      _afg.start();
    } catch (err) {
      console.log(err);
    }
  },
};


function showafg(e, t) {
  adCallBack = e;
  isRewardAd = t;
  try {
    _afg.start_afg();
  } catch (err) {
    console.log(err);
  }
}


let _is_show_banner = 1;
let adCallBack = null;
let isRewardAd = false;
let AFGURL = '';
function show_adx_banner() {
  // $('#afc_banner_foot').append(adx2);
}

$(document).ready(function() {
  $('#game_frame').attr('src', './game.html');
  $(window).trigger('resize');
  // showafg(function() {
  //   $('#game_frame').attr('src', './gameIndex.html');
  //   $(window).trigger('resize');
  // }, true);
  if (_is_show_banner === 1) {
    show_adx_banner();
  }
});

$(window).resize(function() {
  if (_is_show_banner === 1) {
    _afg.adlength = 65;
    $('#gameframediv,#afgContainer').height($(window).height() - _afg.adlength);
    $('#afgContainer').width($(window).width());
    $('#afc_banner_foot').height(_afg.adlength);
    if(BANNER_TOP === 1){
      // banner top
      // $('#ad_tips').css('top', 50);
      $('#afc_banner_foot').css('top', 0);
      $('#afc_banner_foot').css('margin-bottom', 15);
      $('#gameframediv,#afgContainer').css('bottom', 0);
    }
    else{
      // banner bottom
      // $('#ad_tips').css('bottom', 50);
      $('#afc_banner_foot').css('bottom', 0);
      $('#afc_banner_foot').css('margin-top', 15);
      $('#gameframediv,#afgContainer').css('top', 0);
    }
  } else {
    $('#gameframediv').height($(window).height());
    _afg.adlength = 0;
    $('#afgContainer').height($('#gameframediv').height());
    $('#afgContainer').width($(window).width());
    $('#afc_banner_foot').hide();
    // $('#ad_tips').hide();
  }

});
let mobileResizeCallback = function() {
  if(window.innerWidth < window.innerHeight && LANDSCAPE_LOCKED) {
    document.getElementById('orientation').style.display = 'block';
  }
  else{
    document.getElementById('orientation').style.display = 'none';
  }
}

let isMobile = /Mobile|mini|Fennec|Android|iP(ad|od|hone)/.test(navigator.appVersion);
isMobile && mobileResizeCallback();
isMobile && window.addEventListener('resize', function() {
  mobileResizeCallback();
});
