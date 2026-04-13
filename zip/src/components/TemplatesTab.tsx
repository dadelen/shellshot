import { Plus, Smartphone, Trash2 } from "lucide-react";
import { motion } from "motion/react";

const container = {
  hidden: { opacity: 0 },
  show: {
    opacity: 1,
    transition: { staggerChildren: 0.1 }
  }
};

const item = {
  hidden: { opacity: 0, y: 20, scale: 0.95 },
  show: { opacity: 1, y: 0, scale: 1, transition: { stiffness: 300, damping: 24 } }
};

export function TemplatesTab() {
  const templates = [
    { name: "Default Device", desc: "内置 · 1440 × 2840 · 居中裁切", active: false },
    { name: "Center Hole Black", desc: "内置 · 1000 × 1800 · 居中裁切", active: false },
    { name: "三星s26u", desc: "用户 · 1176 × 2000 · 居中裁切", active: true },
  ];

  return (
    <motion.div 
      variants={container}
      initial="hidden"
      animate="show"
      className="p-6 pt-14 space-y-6 sm:pt-16 pb-36"
    >
      <div className="flex justify-between items-end mb-8">
        <motion.h1 variants={item} className="text-3xl font-bold tracking-tight text-neutral-900 dark:text-white">
          手机模板库
        </motion.h1>
        <motion.div variants={item} className="bg-white/70 dark:bg-black/40 backdrop-blur-md rounded-full px-3 py-1.5 inline-flex items-center gap-2 shadow-[0_2px_10px_rgb(0,0,0,0.04)] dark:shadow-[0_2px_10px_rgb(0,0,0,0.2)] border border-white/80 dark:border-white/10">
          <div className="w-1.5 h-1.5 rounded-full bg-[#007AFF] animate-pulse"></div>
          <span className="text-[13px] font-medium text-neutral-600 dark:text-neutral-400">共 {templates.length} 条</span>
        </motion.div>
      </div>

      <motion.button 
        variants={item}
        whileTap={{ scale: 0.97 }}
        className="w-full group bg-transparent rounded-[2rem] p-4 border-2 border-dashed border-neutral-300/80 dark:border-neutral-600/80 flex items-center justify-center gap-2 text-neutral-500 dark:text-neutral-400 font-medium hover:bg-blue-50/50 dark:hover:bg-blue-900/20 hover:border-blue-300 dark:hover:border-blue-700 hover:text-blue-600 dark:hover:text-blue-400 transition-all"
      >
        <div className="w-[32px] h-[32px] rounded-[10px] bg-neutral-100/80 dark:bg-neutral-800/80 border border-neutral-200/60 dark:border-neutral-700/60 flex items-center justify-center shadow-sm group-hover:bg-blue-100/50 dark:group-hover:bg-blue-900/40 group-hover:border-blue-200 dark:group-hover:border-blue-800 transition-colors">
          <Plus className="w-4 h-4 text-neutral-400 dark:text-neutral-500 group-hover:text-blue-600 dark:group-hover:text-blue-400 transition-colors" />
        </div>
        <span className="text-[15px]">新增模板</span>
      </motion.button>

      <div className="space-y-4">
        {templates.map((tpl, i) => (
          <motion.div 
            variants={item}
            key={i} 
            className={`bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[2rem] p-5 shadow-[0_8px_30px_rgb(0,0,0,0.04)] dark:shadow-[0_8px_30px_rgb(0,0,0,0.2)] border ${tpl.active ? 'border-blue-200 dark:border-blue-800 ring-4 ring-blue-500/10 dark:ring-blue-500/20' : 'border-white/60 dark:border-white/10'} flex items-center gap-4 transition-all`}
          >
            <div className={`w-[46px] h-[46px] rounded-[12px] flex items-center justify-center shrink-0 border shadow-sm ${tpl.active ? 'bg-blue-50 dark:bg-blue-900/30 text-blue-500 dark:text-blue-400 border-blue-100/50 dark:border-blue-800/50' : 'bg-neutral-50 dark:bg-neutral-800/50 text-neutral-400 dark:text-neutral-500 border-neutral-200/60 dark:border-neutral-700/60'}`}>
              <Smartphone className="w-6 h-6" strokeWidth={1.5} />
            </div>
            <div className="flex-1 min-w-0">
              <h3 className="text-[17px] font-semibold text-neutral-900 dark:text-white truncate mb-1">{tpl.name}</h3>
              <p className="text-[13px] text-neutral-500 dark:text-neutral-400 truncate">{tpl.desc}</p>
            </div>
            <div className="flex items-center gap-2">
              {!tpl.active && (
                <motion.button
                  whileTap={{ scale: 0.9 }}
                  className="w-[34px] h-[34px] rounded-full flex items-center justify-center bg-red-50 dark:bg-red-900/20 text-red-500 dark:text-red-400 border border-red-100/50 dark:border-red-900/50 hover:bg-red-100 dark:hover:bg-red-900/40 transition-colors shadow-sm"
                >
                  <Trash2 className="w-4 h-4" />
                </motion.button>
              )}
              <motion.button 
                whileTap={{ scale: 0.95 }}
                className={`rounded-full px-4 h-[34px] text-[13px] font-medium transition-colors border ${
                  tpl.active 
                    ? 'bg-[#007AFF] text-white shadow-[0_4px_12px_rgba(0,122,255,0.3)] border-transparent' 
                    : 'bg-white/70 dark:bg-white/10 text-neutral-600 dark:text-neutral-300 hover:bg-white/90 dark:hover:bg-white/20 border-white/80 dark:border-white/10 shadow-sm'
                }`}
              >
                {tpl.active ? '当前使用' : '使用'}
              </motion.button>
            </div>
          </motion.div>
        ))}
      </div>
    </motion.div>
  );
}
