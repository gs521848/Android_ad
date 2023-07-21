window.gameData = {};

class GameIntroductionPage {
  baseBodyElement = ' \
    <div id="startContainer"> \
      <div class="container" id="app" style=""> \
        <div id="detailMain"> \
          <div class="desc_box" id="descBox"> \
          </div> \
        </div> \
      </div> \
    </div>';

  gameTitleCard = ' \
    <section class="detail-wrapper"> \
      <div class="detail-wrapperSon" id="detail-wrapperSon1"> \
        <div class="displayFlex detail-wrapper-cont"> \
          <div><img src="gamelogo.png" class="logo" alt="Game" title="Game"></div> \
          <div class="detail-text"> \
            <div class="name" id="gameTitle"></div> \
            <div class="tag" style="color: #366B21;"></div> \
            <div class="displayFlex rating"> \
              <div class="star-box box-shadow" style="float: left;margin-right: 2px;width: 2.5rem;"> \
                <div class="star-rate" style="width:100%"></div> \
              </div> \
            </div> \
          </div> \
        </div> \
      </div> \
    </section>';

  gameDescriptionCard = ' \
    <section class="detail-wrapper2"> \
      <div class="detail-wrapperSon" id="detail-wrapperSon2"> \
        <div class="desc"></div> \
      </div> \
    </section>';

  lodingPart = ' \
    <div id="aircraft" class="demo" style="display:block"> \
      <div class="container"> \
        <div class="row"> \
          <div class="col-md-offset-3 col-md-6 col-xs-offset-0_5 col-xs-10"> \
            <div class="progress"> \
              <div class="progress-bar" style="width: 100%; background:#005394;"> \
                <span id="num">0%</span> \
              </div> \
            </div> \
          </div> \
        </div> \
      </div> \
    </div>';

  playButton = ' \
    <a href="gameIndex.html" id="playBtn" class="play_btn" style="display:none"> \
      <div class="play-btn-wrap"> \
        <div class="play-btn">PLAY</div> \
      </div> \
    </a>';

  continueButton = ' \
    <a id="playBtn" class="play_btn" style="display:block"> \
      <div class="play-btn-wrap"> \
        <div class="play-btn">continue</div> \
      </div> \
    </a>';

  adsPart = ' \
    <div class="adsbox" style="clear: both;margin: 10px 0 ;"> \
      <div class="adStyle"> \
        <div class="adStyleTop">ADVERTISEMENT</div> \
        <div id="game_note_slot1" style="height: 350px;display: flex;margin: 10px 0;justify-content: center;overflow: hidden"></div> \
      </div> \
    </div>';

  constructor() {
    if (window.gameData !== null) {
      $.ajax({
        type: "GET",
        url: "./manifest.json",
        async: false,
        dataType: "json",
        success: function(data) {
          window.gameData = data;
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
          location.reload();
        }
      });
    }

    this.callback = null;
  }

  initGameIntroductionPage(e) {
    $('body').append(e.value);
    this.callback = e.callback || null;
  }

  selectElementsToFill(elementList) {
    elementList.forEach((e) => {
      $("#" + e.parent).append(e.value);
    });

    this.callback && this.callback();
  }

  countdown() {
    let Loader = function() {
      let meter = document.querySelector('#num'),
          k, i = 1, j = 1, progress = document.getElementsByClassName('progress')[0],
          counter = function() {
            if (j < 100) {
              i = trunc(($("#num").position().left / progress.offsetWidth) * 100);
              j++;
              if (j > i) i = j;
              meter.innerHTML = i.toString();
            } else {
              window.clearInterval(k);
            }
          };

      return {
        init: function(options) {
          options = options || {};
          let time = options.time ? options.time : 0, interval = time / 100;
          k = window.setInterval(counter, interval);
          setTimeout(function() {
            document.getElementById('aircraft').style.display = 'none';
            document.getElementById('playBtn').style.display = 'block';
          }, time);
        },
      };
    }();

    function trunc(n) {
      if (n > -0x80000000 && n < 0x80000000) {
        return n & 0xFFFFFFFF;
      }
      return Math.trunc(n);
    }

    Loader.init({
      time: 8000,
    });
  }

  decorateStartPage() {
    !function(e) {
      var t = {};
      function n(i) {
        if (t[i])
          return t[i].exports;
        var o = t[i] = {
          i: i,
          l: !1,
          exports: {}
        };
        return e[i].call(o.exports, o, o.exports, n),
            o.l = !0,
            o.exports
      }
      n.m = e,
          n.c = t,
          n.d = function(e, t, i) {
            n.o(e, t) || Object.defineProperty(e, t, {
              enumerable: !0,
              get: i
            })
          }
          ,
          n.r = function(e) {
            "undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {
              value: "Module"
            }),
                Object.defineProperty(e, "__esModule", {
                  value: !0
                })
          }
          ,
          n.t = function(e, t) {
            if (1 & t && (e = n(e)),
            8 & t)
              return e;
            if (4 & t && "object" == typeof e && e && e.__esModule)
              return e;
            var i = Object.create(null);
            if (n.r(i),
                Object.defineProperty(i, "default", {
                  enumerable: !0,
                  value: e
                }),
            2 & t && "string" != typeof e)
              for (var o in e)
                n.d(i, o, function(t) {
                  return e[t]
                }
                    .bind(null, o));
            return i
          }
          ,
          n.n = function(e) {
            var t = e && e.__esModule ? function() {
                  return e.default
                }
                : function() {
                  return e
                }
            ;
            return n.d(t, "a", t),
                t
          }
          ,
          n.o = function(e, t) {
            return Object.prototype.hasOwnProperty.call(e, t)
          }
          ,
          n.p = "/",
          n(n.s = 31)
    }({
      31: function(e, t, n) {
        e.exports = n(32)
      },
      32: function(e, t, n) {
        "use strict";
        n(4)
      },
      4: function(e, t) {
        !function(e, t) {
          var n, i = e.document, o = i.documentElement, a = i.querySelector('meta[name="viewport"]'), r = i.querySelector('meta[name="flexible"]'), s = 0, d = 0, c = t.flexible || (t.flexible = {});
          if (a) {
            console.warn("将根据已有的meta标签来设置缩放比例");
            var l = a.getAttribute("content").match(/initial\-scale=([\d\.]+)/);
            l && (d = parseFloat(l[1]),
                s = parseInt(1 / d))
          } else if (r) {
            var u = r.getAttribute("content");
            if (u) {
              var f = u.match(/initial\-dpr=([\d\.]+)/)
                  , m = u.match(/maximum\-dpr=([\d\.]+)/);
              f && (s = parseFloat(f[1]),
                  d = parseFloat((1 / s).toFixed(2))),
              m && (s = parseFloat(m[1]),
                  d = parseFloat((1 / s).toFixed(2)))
            }
          }
          if (!s && !d) {
            e.navigator.appVersion.match(/android/gi);
            var v = e.navigator.appVersion.match(/iphone/gi)
                , p = e.devicePixelRatio;
            d = 1 / (s = v ? 3 <= p && (!s || 3 <= s) ? 3 : 2 <= p && (!s || 2 <= s) ? 2 : 1 : 1)
          }
          if (o.setAttribute("data-dpr", s),
              !a)
            if ((a = i.createElement("meta")).setAttribute("name", "viewport"),
                a.setAttribute("content", "initial-scale=" + d + ", maximum-scale=" + d + ", minimum-scale=" + d + ", user-scalable=no"),
                o.firstElementChild)
              o.firstElementChild.appendChild(a);
            else {
              var g = i.createElement("div");
              g.appendChild(a),
                  i.write(g.innerHTML)
            }
          function h() {
            var t = o.getBoundingClientRect().width;
            540 < t / s && (t = 540 * s);
            var n = t / 10;
            o.style.fontSize = n + "px",
                c.rem = e.rem = n
          }
          e.addEventListener("resize", function() {
            clearTimeout(n),
                n = setTimeout(h, 300)
          }, !1),
              e.addEventListener("pageshow", function(e) {
                e.persisted && (clearTimeout(n),
                    n = setTimeout(h, 300))
              }, !1),
              "complete" === i.readyState ? i.body.style.fontSize = 12 * s + "px" : i.addEventListener("DOMContentLoaded", function(e) {
                i.body.style.fontSize = 12 * s + "px"
              }, !1),
              h(),
              c.dpr = e.dpr = s,
              c.refreshRem = h,
              c.rem2px = function(e) {
                var t = parseFloat(e) * this.rem;
                return "string" == typeof e && e.match(/rem$/) && (t += "px"),
                    t
              }
              ,
              c.px2rem = function(e) {
                var t = parseFloat(e) / this.rem;
                return "string" == typeof e && e.match(/px$/) && (t += "rem"),
                    t
              }
        }(window, window.lib || (window.lib = {}))
      }
    });
  }
}

class gameContainerPage {

}

let Alarm = function (time, countFunc, endFunc) {
  this.time = time;
  this.countFunc = countFunc;
  this.endFunc = endFunc;
  this.flag = function() {};
};

Alarm.prototype.start = function () {
  let self = this;

  self.flag = setInterval(function () {
    if (self.time < 0) {
      clearInterval(self.flag);
      self.endFunc && self.endFunc();
      console.log('计时结束');
    } else {
      self.countFunc && self.countFunc();
      self.time--;
    }
  }, 1000);
}
