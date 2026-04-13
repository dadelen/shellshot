import { useState, useEffect } from "react";
import { Badge } from "@/components/ui/badge";
import { motion } from "motion/react";
import { Play, Settings2, FileText, Bug, Smartphone, Moon, Sun } from "lucide-react";

const container = {
  hidden: { opacity: 0 },
  show: {
    opacity: 1,
    transition: { staggerChildren: 0.08 }
  }
};

const item = {
  hidden: { opacity: 0, y: 20, scale: 0.95 },
  show: { opacity: 1, y: 0, scale: 1, transition: { stiffness: 300, damping: 24 } }
};

export function HomeTab() {
  const [isDark, setIsDark] = useState(false);

  useEffect(() => {
    setIsDark(document.documentElement.classList.contains('dark'));
  }, []);

  const toggleDarkMode = (event: React.MouseEvent) => {
    const isDark = document.documentElement.classList.contains('dark');
    
    if (!document.startViewTransition) {
      document.documentElement.classList.toggle('dark');
      setIsDark(!isDark);
      return;
    }

    const x = event.clientX;
    const y = event.clientY;
    const endRadius = Math.hypot(
      Math.max(x, innerWidth - x),
      Math.max(y, innerHeight - y)
    );

    const transition = document.startViewTransition(() => {
      document.documentElement.classList.toggle('dark');
      setIsDark(!isDark);
    });

    transition.ready.then(() => {
      const clipPath = [
        `circle(0px at ${x}px ${y}px)`,
        `circle(${endRadius}px at ${x}px ${y}px)`,
      ];
      document.documentElement.animate(
        {
          clipPath: !isDark ? clipPath : [...clipPath].reverse(),
        },
        {
          duration: 500,
          easing: "ease-in-out",
          pseudoElement: !isDark
            ? "::view-transition-new(root)"
            : "::view-transition-old(root)",
        }
      );
    });
  };

  return (
    <motion.div 
      variants={container}
      initial="hidden"
      animate="show"
      className="p-6 pt-14 space-y-6 sm:pt-16 pb-36"
    >
      <div className="flex justify-between items-center mb-2">
        <motion.h1 variants={item} className="text-3xl font-bold tracking-tight text-neutral-900 dark:text-white">
          ShellShot
        </motion.h1>
        <motion.button
          variants={item}
          whileTap={{ scale: 0.9 }}
          onClick={toggleDarkMode}
          className="w-10 h-10 rounded-full bg-white/60 dark:bg-black/40 backdrop-blur-md border border-white/60 dark:border-white/10 shadow-sm flex items-center justify-center text-neutral-700 dark:text-neutral-300"
        >
          {isDark ? <Sun className="w-5 h-5" /> : <Moon className="w-5 h-5" />}
        </motion.button>
      </div>

      {/* Status Card - Glassmorphism */}
      <motion.div variants={item} className="relative overflow-hidden bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] p-6 shadow-[0_8px_30px_rgb(0,0,0,0.04)] dark:shadow-[0_8px_30px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10">
        <div className="absolute top-0 right-0 w-32 h-32 bg-blue-500/10 dark:bg-blue-500/20 rounded-full blur-3xl -mr-10 -mt-10 pointer-events-none"></div>
        
        <div className="flex justify-between items-center mb-8 relative z-10">
          <div className="flex items-center gap-3">
            <div className="w-3 h-3 rounded-full bg-neutral-300 dark:bg-neutral-600 relative">
              <div className="absolute inset-0 rounded-full bg-neutral-300 dark:bg-neutral-600 animate-ping opacity-50"></div>
            </div>
            <h2 className="text-xl font-semibold text-neutral-900 dark:text-white">监听服务未启动</h2>
          </div>
          <Badge variant="secondary" className="bg-white/50 dark:bg-white/10 backdrop-blur-md text-neutral-500 dark:text-neutral-400 font-medium px-3 py-1 rounded-full border border-white/60 dark:border-white/10 shadow-sm">未开启</Badge>
        </div>

        <motion.button 
          whileTap={{ scale: 0.97 }}
          className="w-full relative group overflow-hidden bg-[#1C1C1E] dark:bg-white text-white dark:text-black rounded-[1.2rem] h-[52px] text-[15px] font-medium mb-4 shadow-[0_4px_14px_rgba(0,0,0,0.15)] dark:shadow-[0_4px_14px_rgba(255,255,255,0.15)] flex items-center justify-center gap-2"
        >
          <Play className="w-4 h-4 fill-current" />
          <span>启动监听</span>
        </motion.button>

        <div className="flex gap-3 relative z-10">
          <motion.button whileTap={{ scale: 0.95 }} className="flex-1 flex items-center justify-center gap-2 bg-white/70 dark:bg-white/10 hover:bg-white/90 dark:hover:bg-white/20 backdrop-blur-md rounded-[1.2rem] h-[46px] text-[14px] text-neutral-700 dark:text-neutral-300 font-medium transition-colors shadow-[0_2px_8px_rgba(0,0,0,0.04)] border border-white/80 dark:border-white/10">
            <Settings2 className="w-4 h-4" />
            <span>权限设置</span>
          </motion.button>
          <motion.button whileTap={{ scale: 0.95 }} className="flex-1 flex items-center justify-center gap-2 bg-white/70 dark:bg-white/10 hover:bg-white/90 dark:hover:bg-white/20 backdrop-blur-md rounded-[1.2rem] h-[46px] text-[14px] text-neutral-700 dark:text-neutral-300 font-medium transition-colors shadow-[0_2px_8px_rgba(0,0,0,0.04)] border border-white/80 dark:border-white/10">
            <FileText className="w-4 h-4" />
            <span>查看日志</span>
          </motion.button>
        </div>
      </motion.div>

      {/* Debug Mode */}
      <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] p-5 shadow-[0_4px_20px_rgb(0,0,0,0.03)] dark:shadow-[0_4px_20px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10 flex justify-between items-center">
        <div className="flex items-center gap-4">
          <div className="w-[34px] h-[34px] rounded-[10px] bg-emerald-50 dark:bg-emerald-500/10 border border-emerald-100/50 dark:border-emerald-500/20 flex items-center justify-center text-emerald-500 shadow-sm">
            <Bug className="w-[18px] h-[18px]" strokeWidth={2} />
          </div>
          <span className="text-[17px] font-medium text-neutral-900 dark:text-white">调试模式</span>
        </div>
        <LiquidGlassSwitch />
      </motion.div>

      {/* Current Template */}
      <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] p-6 shadow-[0_4px_20px_rgb(0,0,0,0.03)] dark:shadow-[0_4px_20px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10">
        <div className="flex justify-between items-start mb-6">
          <div>
            <h3 className="text-xl font-semibold text-neutral-900 dark:text-white mb-2">三星s26u</h3>
            <div className="flex gap-2">
              <Badge variant="secondary" className="bg-blue-50 dark:bg-blue-500/10 text-blue-600 dark:text-blue-400 font-medium px-2.5 py-0.5 rounded-md border border-blue-100/50 dark:border-blue-500/20">用户</Badge>
              <Badge variant="secondary" className="bg-white/70 dark:bg-white/10 text-neutral-600 dark:text-neutral-400 font-medium px-2.5 py-0.5 rounded-md border border-white/80 dark:border-white/10">1176 × 2000</Badge>
            </div>
          </div>
          <div className="w-[42px] h-[42px] rounded-[12px] bg-blue-50 dark:bg-blue-500/10 border border-blue-100/50 dark:border-blue-500/20 flex items-center justify-center text-blue-500 shadow-sm">
            <Smartphone className="w-6 h-6" strokeWidth={1.5} />
          </div>
        </div>
        
        <div className="bg-white/40 dark:bg-black/20 backdrop-blur-md rounded-2xl p-6 flex justify-center items-center border border-white/60 dark:border-white/5 shadow-inner">
          <div className="aspect-[1/2] w-24 border-[6px] border-neutral-800 dark:border-neutral-700 rounded-[1.5rem] relative shadow-lg bg-neutral-900/5 dark:bg-white/5">
            <div className="absolute top-1.5 left-1/2 -translate-x-1/2 w-1.5 h-1.5 bg-neutral-800 dark:bg-neutral-700 rounded-full"></div>
          </div>
        </div>
      </motion.div>

      {/* Recent Log */}
      <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] p-6 shadow-[0_4px_20px_rgb(0,0,0,0.03)] dark:shadow-[0_4px_20px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10">
        <div className="flex justify-between items-center mb-5">
          <span className="text-xs font-medium text-neutral-500 dark:text-neutral-400 uppercase tracking-wider">最近处理</span>
          <Badge variant="secondary" className="bg-emerald-50 dark:bg-emerald-500/10 text-emerald-600 dark:text-emerald-400 font-medium px-2.5 py-0.5 rounded-full border border-emerald-100/50 dark:border-emerald-500/20 flex items-center gap-1.5">
            <div className="w-1.5 h-1.5 rounded-full bg-emerald-500"></div>
            处理成功
          </Badge>
        </div>
        
        <div className="space-y-3">
          <div className="flex justify-between items-center py-2 border-b border-white/40 dark:border-white/10">
            <span className="text-[15px] text-neutral-500 dark:text-neutral-400">触发来源</span>
            <span className="text-[15px] text-neutral-900 dark:text-white font-medium">file_observer</span>
          </div>
          <div className="flex justify-between items-center py-2 border-b border-white/40 dark:border-white/10">
            <span className="text-[15px] text-neutral-500 dark:text-neutral-400">原图处理</span>
            <span className="text-[15px] text-neutral-900 dark:text-white font-medium">已删除原图</span>
          </div>
        </div>
        <div className="mt-4 bg-white/40 dark:bg-black/20 backdrop-blur-md rounded-xl p-3 border border-white/60 dark:border-white/5">
          <span className="text-xs font-mono text-neutral-600 dark:text-neutral-400 break-all">screenshot_20260408_194952.png</span>
        </div>
      </motion.div>
    </motion.div>
  );
}

function LiquidGlassSwitch({ defaultChecked }: { defaultChecked?: boolean }) {
  const [isOn, setIsOn] = useState(defaultChecked || false);
  const [isAnimating, setIsAnimating] = useState(false);
  
  useEffect(() => {
    if (isAnimating) {
      const timer = setTimeout(() => {
        setIsAnimating(false);
      }, 150);
      return () => clearTimeout(timer);
    }
  }, [isAnimating]);

  const handleClick = () => {
    setIsOn(!isOn);
    setIsAnimating(true);
  };

  return (
    <button 
      onClick={handleClick}
      className={`relative w-[52px] h-[32px] rounded-full p-[2px] flex items-center transition-colors duration-300 ease-out outline-none shadow-inner ${isOn ? 'bg-[#34C759]' : 'bg-neutral-200/80 dark:bg-neutral-700/80 border border-neutral-300/50 dark:border-neutral-600/50'}`}
    >
      <motion.div 
        layout
        initial={false}
        animate={{ 
          x: isOn ? (isAnimating ? 0 : 20) : (isAnimating ? 8 : 0),
          width: isAnimating ? 40 : 28,
          height: isAnimating ? 22 : 28
        }}
        transition={{ type: "spring", stiffness: 500, damping: 30 }}
        className="rounded-full relative overflow-hidden"
      >
        {/* Solid White Bead (Resting State) */}
        <motion.div 
          animate={{ opacity: isAnimating ? 0 : 1 }}
          transition={{ duration: 0.15 }}
          className="absolute inset-0 bg-white rounded-full shadow-[0_3px_8px_rgba(0,0,0,0.15),0_3px_1px_rgba(0,0,0,0.06)]"
        />

        {/* Glassy Slider (Animating State) */}
        <motion.div 
          animate={{ opacity: isAnimating ? 1 : 0 }}
          transition={{ duration: 0.15 }}
          className="absolute inset-0 bg-gradient-to-b from-white/80 via-white/30 to-white/5 backdrop-blur-md rounded-full border-[0.5px] border-white/80 shadow-[0_4px_8px_rgba(0,0,0,0.12),inset_0_1px_2px_rgba(255,255,255,1),inset_0_3px_6px_rgba(255,255,255,0.6),inset_0_-1px_2px_rgba(0,0,0,0.05)]"
        >
          <div className="absolute top-0 inset-x-1.5 h-[35%] bg-gradient-to-b from-white/90 to-transparent rounded-full opacity-90 blur-[0.5px]"></div>
          <div className="absolute bottom-0 inset-x-2 h-[20%] bg-gradient-to-t from-white/40 to-transparent rounded-full opacity-60 blur-[1px]"></div>
        </motion.div>
      </motion.div>
    </button>
  );
}
