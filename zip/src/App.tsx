import { useState, useEffect } from 'react';
import { Home, LayoutTemplate, Settings, ScrollText } from 'lucide-react';
import { motion, AnimatePresence } from 'motion/react';
import { HomeTab } from './components/HomeTab';
import { TemplatesTab } from './components/TemplatesTab';
import { SettingsTab } from './components/SettingsTab';
import { LogsTab } from './components/LogsTab';

export default function App() {
  const [activeTab, setActiveTab] = useState('home');
  const [isAnimating, setIsAnimating] = useState(false);

  const handleTabChange = (tab: string) => {
    if (tab === activeTab) return;
    setIsAnimating(true);
    setActiveTab(tab);
  };

  useEffect(() => {
    if (isAnimating) {
      const timer = setTimeout(() => {
        setIsAnimating(false);
      }, 450); // Match the slide transition duration
      return () => clearTimeout(timer);
    }
  }, [isAnimating, activeTab]);

  return (
    <div className="min-h-screen bg-neutral-900 flex items-center justify-center p-4 sm:p-8 font-sans selection:bg-blue-200">
      {/* Mobile Device Wrapper */}
      <div className="w-full max-w-[400px] h-[100dvh] sm:h-[850px] bg-[#F2F2F7] dark:bg-black relative overflow-hidden sm:rounded-[3rem] sm:border-[14px] sm:border-black shadow-2xl flex flex-col ring-1 ring-white/10">
        
        {/* Premium Mesh Gradient Background (Colorful but non-AI) */}
        <div className="absolute inset-0 pointer-events-none z-0 overflow-hidden bg-[#F4F6F9] dark:bg-[#1C1C1E]">
          {/* Soft top studio light */}
          <div className="absolute top-0 inset-x-0 h-[40%] bg-gradient-to-b from-white/80 dark:from-black/80 to-transparent z-10"></div>
          
          {/* Colorful but elegant ambient blobs (Apple-like wallpaper vibe, avoiding neon AI colors) */}
          <div className="absolute -top-[10%] -right-[10%] w-[70%] h-[60%] rounded-full bg-sky-200/40 dark:bg-sky-900/20 blur-[90px]"></div>
          <div className="absolute bottom-[10%] -left-[20%] w-[60%] h-[60%] rounded-full bg-indigo-100/50 dark:bg-indigo-900/20 blur-[100px]"></div>
          <div className="absolute top-[20%] -left-[10%] w-[50%] h-[50%] rounded-full bg-rose-100/30 dark:bg-rose-900/20 blur-[80px]"></div>
        </div>

        {/* Fake Dynamic Island (Desktop only) */}
        <div className="absolute top-0 inset-x-0 h-8 z-50 justify-center pt-2 hidden sm:flex pointer-events-none">
          <div className="w-32 h-7 bg-black rounded-full shadow-inner"></div>
        </div>

        {/* Main Content Area */}
        <div className="flex-1 overflow-y-auto scrollbar-hide relative z-10">
          <AnimatePresence mode="wait">
            <motion.div
              key={activeTab}
              initial={{ opacity: 0, y: 15, scale: 0.98, filter: 'blur(8px)' }}
              animate={{ opacity: 1, y: 0, scale: 1, filter: 'blur(0px)' }}
              exit={{ opacity: 0, y: -15, scale: 0.98, filter: 'blur(8px)' }}
              transition={{ duration: 0.4, ease: [0.23, 1, 0.32, 1] }}
              className="min-h-full"
            >
              {activeTab === 'home' && <HomeTab />}
              {activeTab === 'templates' && <TemplatesTab />}
              {activeTab === 'settings' && <SettingsTab />}
              {activeTab === 'logs' && <LogsTab />}
            </motion.div>
          </AnimatePresence>
        </div>

        {/* Video-Replica Floating Dock */}
        <div className="absolute bottom-8 inset-x-0 flex justify-center z-50 pointer-events-none">
          {/* Dock Container - More transparent to let the slider pop */}
          <div className="bg-white/20 dark:bg-black/40 backdrop-blur-[40px] border border-white/40 dark:border-white/10 shadow-[0_8px_32px_rgba(0,0,0,0.1)] dark:shadow-[0_8px_32px_rgba(0,0,0,0.3)] rounded-full p-1.5 flex items-center gap-1 pointer-events-auto">
            <NavItem id="home" icon={<Home className="w-[22px] h-[22px]" strokeWidth={2.5} />} label="首页" isActive={activeTab === 'home'} isAnimating={isAnimating} onClick={() => handleTabChange('home')} />
            <NavItem id="templates" icon={<LayoutTemplate className="w-[22px] h-[22px]" strokeWidth={2.5} />} label="模板" isActive={activeTab === 'templates'} isAnimating={isAnimating} onClick={() => handleTabChange('templates')} />
            <NavItem id="settings" icon={<Settings className="w-[22px] h-[22px]" strokeWidth={2.5} />} label="设置" isActive={activeTab === 'settings'} isAnimating={isAnimating} onClick={() => handleTabChange('settings')} />
            <NavItem id="logs" icon={<ScrollText className="w-[22px] h-[22px]" strokeWidth={2.5} />} label="日志" isActive={activeTab === 'logs'} isAnimating={isAnimating} onClick={() => handleTabChange('logs')} />
          </div>
        </div>
      </div>
    </div>
  );
}

function NavItem({ id, icon, label, isActive, isAnimating, onClick }: { id: string; icon: React.ReactNode; label: string; isActive: boolean; isAnimating: boolean; onClick: () => void }) {
  return (
    <button
      onClick={onClick}
      className={`relative flex items-center justify-center h-14 rounded-full transition-all duration-500 ease-out z-10 outline-none ${
        isActive ? 'w-[72px]' : 'w-14'
      }`}
    >
      {/* Transient Hyper-Realistic Glass Slider */}
      {isActive && (
        <motion.div 
          layoutId="dock-glass-slider"
          initial={{ opacity: 1 }}
          animate={{ opacity: isAnimating ? 1 : 0 }}
          transition={{ 
            type: "spring", stiffness: 350, damping: 30,
            opacity: { duration: 0.3, delay: isAnimating ? 0 : 0.15 } // Delay fade out slightly
          }}
          className="absolute inset-0 z-0 pointer-events-none"
        >
          {/* Glass Body with complex shadows and highlights */}
          <div className="absolute inset-0 bg-gradient-to-b from-white/80 via-white/30 to-white/5 dark:from-white/20 dark:via-white/5 dark:to-transparent backdrop-blur-lg rounded-full border-[0.5px] border-white/80 dark:border-white/20 shadow-[0_8px_16px_rgba(0,0,0,0.12),inset_0_1px_2px_rgba(255,255,255,1),inset_0_4px_10px_rgba(255,255,255,0.6),inset_0_-2px_4px_rgba(0,0,0,0.05)] dark:shadow-[0_8px_16px_rgba(0,0,0,0.3),inset_0_1px_1px_rgba(255,255,255,0.5),inset_0_4px_10px_rgba(255,255,255,0.2),inset_0_-2px_4px_rgba(0,0,0,0.2)] overflow-hidden">
            {/* Top Glare / Reflection */}
            <div className="absolute top-0 inset-x-1.5 h-[35%] bg-gradient-to-b from-white/90 dark:from-white/30 to-transparent rounded-full opacity-90 blur-[0.5px]"></div>
            {/* Bottom Edge Glow */}
            <div className="absolute bottom-0 inset-x-3 h-[20%] bg-gradient-to-t from-white/40 dark:from-white/10 to-transparent rounded-full opacity-60 blur-[1px]"></div>
          </div>
        </motion.div>
      )}
      
      <div className="relative z-10 flex flex-col items-center justify-center h-full w-full">
        {/* Icon Container */}
        <motion.div
          animate={{ 
            y: isActive ? -6 : 0,
            color: isActive ? '#007AFF' : 'var(--icon-color)' // Authentic iOS Blue vs neutral-500
          }}
          transition={{ type: "spring", stiffness: 500, damping: 35 }}
          className="relative z-10 flex items-center justify-center text-neutral-500 dark:text-neutral-400"
          style={{ '--icon-color': 'currentColor' } as React.CSSProperties}
        >
          {icon}
        </motion.div>
        
        {/* Text Container */}
        <motion.span
          animate={{ 
            opacity: isActive ? 1 : 0, 
            y: isActive ? 10 : 15,
            scale: isActive ? 1 : 0.8
          }}
          transition={{ type: "spring", stiffness: 500, damping: 35 }}
          className="absolute z-10 text-[11px] font-bold tracking-wide pointer-events-none"
          style={{ color: '#007AFF' }} // Authentic iOS Blue
        >
          {label}
        </motion.span>
      </div>
    </button>
  );
}
