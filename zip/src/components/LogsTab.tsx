import { Badge } from "@/components/ui/badge";
import { motion } from "motion/react";

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

export function LogsTab() {
  const logs = [
    {
      service: "AutoShellService",
      time: "19:49:56",
      title: "监听链已挂起 reason=cleanup:onDestroy",
      desc: "监听链已挂起 reason=cleanup:onDestroy",
      type: "信息",
      color: "blue"
    },
    {
      service: "AutoShellService",
      time: "19:49:56",
      title: "服务已销毁 explicitStop=true",
      desc: "服务已销毁 explicitStop=true reaso...",
      type: "警告",
      color: "amber"
    },
    {
      service: "ScreenState",
      time: "19:49:56",
      title: "屏幕状态监听已停止",
      desc: "屏幕状态监听已停止",
      type: "信息",
      color: "blue"
    },
    {
      service: "AutoShellService",
      time: "19:49:56",
      title: "监听链已挂起 reason=cleanup:stop",
      desc: "监听链已挂起 reason=cleanup:stop:...",
      type: "信息",
      color: "blue"
    },
    {
      service: "AutoShellService",
      time: "19:49:56",
      title: "取消任务 label=foregroundApp",
      desc: "取消任务 label=foregroundAppMon...",
      type: "成功",
      color: "emerald"
    }
  ];

  const getColorClasses = (color: string) => {
    switch (color) {
      case 'amber': return 'bg-amber-500/10 text-amber-600 border-amber-500/20';
      case 'emerald': return 'bg-emerald-500/10 text-emerald-600 border-emerald-500/20';
      default: return 'bg-blue-500/10 text-blue-600 border-blue-500/20';
    }
  };

  const getDotColor = (color: string) => {
    switch (color) {
      case 'amber': return 'bg-amber-500';
      case 'emerald': return 'bg-emerald-500';
      default: return 'bg-blue-500';
    }
  };

  return (
    <motion.div 
      variants={container}
      initial="hidden"
      animate="show"
      className="p-6 pt-14 space-y-6 sm:pt-16 pb-36 relative"
    >
      <div className="flex justify-between items-end mb-8">
        <motion.h1 variants={item} className="text-3xl font-bold tracking-tight text-neutral-900 dark:text-white">
          运行日志
        </motion.h1>
        <motion.div variants={item} className="bg-white/60 dark:bg-black/40 backdrop-blur-md rounded-full px-3 py-1.5 inline-flex items-center gap-2 shadow-[0_2px_10px_rgb(0,0,0,0.04)] dark:shadow-[0_2px_10px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10">
          <div className="w-1.5 h-1.5 rounded-full bg-emerald-500 animate-pulse"></div>
          <span className="text-[13px] font-medium text-neutral-600 dark:text-neutral-400">共 100 条</span>
        </motion.div>
      </div>

      <div className="space-y-4">
        {logs.map((log, i) => (
          <motion.div variants={item} key={i} className="bg-white/60 dark:bg-black/40 backdrop-blur-2xl rounded-[1.5rem] p-5 shadow-[0_8px_30px_rgb(0,0,0,0.04)] dark:shadow-[0_8px_30px_rgb(0,0,0,0.2)] border border-white/60 dark:border-white/10 hover:bg-white/80 dark:hover:bg-white/10 transition-colors">
            <div className="flex justify-between items-start mb-2">
              <div className="flex items-center gap-2">
                <div className={`w-2 h-2 rounded-full ${getDotColor(log.color)} shadow-sm`}></div>
                <span className="font-semibold text-neutral-900 dark:text-white text-[15px]">{log.service}</span>
              </div>
              <Badge variant="secondary" className={`${getColorClasses(log.color)} font-medium px-2 py-0 rounded-md border text-[11px] backdrop-blur-sm`}>
                {log.type}
              </Badge>
            </div>
            <h3 className="text-[14px] font-medium text-neutral-800 dark:text-neutral-200 leading-snug mb-1">{log.title}</h3>
            <div className="flex justify-between items-center mt-3">
              <p className="text-[13px] text-neutral-500 dark:text-neutral-400 truncate max-w-[70%]">{log.desc}</p>
              <span className="text-[12px] font-mono text-neutral-500 dark:text-neutral-400 bg-white/50 dark:bg-white/10 border border-white/60 dark:border-white/10 px-2 py-0.5 rounded-md backdrop-blur-sm">{log.time}</span>
            </div>
          </motion.div>
        ))}
      </div>
    </motion.div>
  );
}
