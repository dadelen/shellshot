import { useState, useEffect } from "react";
import { motion } from "motion/react";
import { Bell, Folder, Image as ImageIcon, ImageOff, Trash2, FileText, Settings as SettingsIcon, ChevronRight } from "lucide-react";

const container = {
  hidden: { opacity: 0 },
  show: {
    opacity: 1,
    transition: { staggerChildren: 0.08 }
  }
};

const item = {
  hidden: { opacity: 0, y: 20, scale: 0.98 },
  show: { opacity: 1, y: 0, scale: 1, transition: { stiffness: 300, damping: 24 } }
};

export function SettingsTab() {
  return (
    <motion.div 
      variants={container}
      initial="hidden"
      animate="show"
      className="p-6 pt-14 space-y-6 sm:pt-16 pb-36"
    >
      <motion.h1 variants={item} className="text-3xl font-bold tracking-tight text-neutral-900 dark:text-white mb-2">
        权限与行为
      </motion.h1>

      {/* Permissions Group */}
      <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] shadow-[0_8px_30px_rgb(0,0,0,0.04)] dark:shadow-[0_8px_30px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10 overflow-hidden">
        <SettingItem icon={<Bell className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-blue-500 dark:text-blue-400" iconBg="bg-blue-50 dark:bg-blue-500/10 border border-blue-100/50 dark:border-blue-500/20" label="通知权限" value="已开启" hasArrow />
        <div className="h-px bg-white/40 dark:bg-white/10 ml-[60px]"></div>
        <SettingItem icon={<Folder className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-amber-500 dark:text-amber-400" iconBg="bg-amber-50 dark:bg-amber-500/10 border border-amber-100/50 dark:border-amber-500/20" label="所有文件访问" value="已开启" hasArrow />
        <div className="h-px bg-white/40 dark:bg-white/10 ml-[60px]"></div>
        <SettingItem icon={<ImageIcon className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-emerald-500 dark:text-emerald-400" iconBg="bg-emerald-50 dark:bg-emerald-500/10 border border-emerald-100/50 dark:border-emerald-500/20" label="图片访问" value="已开启" hasArrow />
      </motion.div>

      {/* Behavior Group */}
      <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] shadow-[0_8px_30px_rgb(0,0,0,0.04)] dark:shadow-[0_8px_30px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10 overflow-hidden">
        <SettingToggle icon={<ImageOff className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-purple-500 dark:text-purple-400" iconBg="bg-purple-50 dark:bg-purple-500/10 border border-purple-100/50 dark:border-purple-500/20" label="媒体兜底" defaultChecked />
        <div className="h-px bg-white/40 dark:bg-white/10 ml-[60px]"></div>
        <SettingToggle icon={<Trash2 className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-red-500 dark:text-red-400" iconBg="bg-red-50 dark:bg-red-500/10 border border-red-100/50 dark:border-red-500/20" label="处理后删除原图" defaultChecked />
        <div className="h-px bg-white/40 dark:bg-white/10 ml-[60px]"></div>
        <SettingToggle icon={<FileText className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-blue-500 dark:text-blue-400" iconBg="bg-blue-50 dark:bg-blue-500/10 border border-blue-100/50 dark:border-blue-500/20" label="保留日志" defaultChecked />
      </motion.div>

      {/* File Group */}
      <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] shadow-[0_8px_30px_rgb(0,0,0,0.04)] dark:shadow-[0_8px_30px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10 overflow-hidden">
        <div className="p-4 px-5 flex justify-between items-center min-h-[60px] bg-transparent">
          <span className="text-[17px] text-neutral-900 dark:text-white">保存位置</span>
          <span className="text-[15px] text-neutral-500 dark:text-neutral-400">原图所在目录</span>
        </div>
        <div className="h-px bg-white/40 dark:bg-white/10 ml-5"></div>
        <div className="p-4 px-5 flex justify-between items-center min-h-[60px] bg-transparent">
          <span className="text-[17px] text-neutral-900 dark:text-white">命名方式</span>
          <span className="text-[15px] text-neutral-500 dark:text-neutral-400">原文件名_shell</span>
        </div>
        <div className="h-px bg-white/40 dark:bg-white/10 ml-5"></div>
        <SettingItem icon={<SettingsIcon className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-neutral-500 dark:text-neutral-400" iconBg="bg-neutral-100 dark:bg-neutral-800 border border-neutral-200/60 dark:border-neutral-700/60" label="切换命名方式" value="切换" hasArrow />
        <div className="h-px bg-white/40 dark:bg-white/10 ml-[60px]"></div>
        <SettingItem icon={<SettingsIcon className="w-[18px] h-[18px]" strokeWidth={2} />} iconColor="text-neutral-500 dark:text-neutral-400" iconBg="bg-neutral-100 dark:bg-neutral-800 border border-neutral-200/60 dark:border-neutral-700/60" label="系统设置" value="打开" hasArrow />
      </motion.div>
    </motion.div>
  );
}

function SettingItem({ icon, iconColor, iconBg, label, value, hasArrow }: { icon: React.ReactNode, iconColor: string, iconBg: string, label: string, value?: string, hasArrow?: boolean }) {
  return (
    <motion.div 
      whileTap={{ backgroundColor: "rgba(255,255,255,0.4)" }}
      className="flex items-center justify-between p-4 px-5 min-h-[60px] bg-transparent cursor-pointer transition-colors hover:bg-white/20 dark:hover:bg-white/10"
    >
      <div className="flex items-center gap-4">
        <div className={`w-[34px] h-[34px] rounded-[10px] ${iconBg} flex items-center justify-center ${iconColor} shadow-sm`}>
          {icon}
        </div>
        <span className="text-[17px] text-neutral-900 dark:text-white">{label}</span>
      </div>
      <div className="flex items-center gap-2">
        {value && <span className="text-[15px] text-neutral-500 dark:text-neutral-400">{value}</span>}
        {hasArrow && <ChevronRight className="w-5 h-5 text-neutral-400 dark:text-neutral-500" />}
      </div>
    </motion.div>
  );
}

function SettingToggle({ icon, iconColor, iconBg, label, defaultChecked }: { icon: React.ReactNode, iconColor: string, iconBg: string, label: string, defaultChecked?: boolean }) {
  return (
    <div className="flex items-center justify-between p-4 px-5 min-h-[60px] bg-transparent hover:bg-white/20 dark:hover:bg-white/10 transition-colors">
      <div className="flex items-center gap-4">
        <div className={`w-[34px] h-[34px] rounded-[10px] ${iconBg} flex items-center justify-center ${iconColor} shadow-sm`}>
          {icon}
        </div>
        <span className="text-[17px] text-neutral-900 dark:text-white">{label}</span>
      </div>
      <LiquidGlassSwitch defaultChecked={defaultChecked} />
    </div>
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
